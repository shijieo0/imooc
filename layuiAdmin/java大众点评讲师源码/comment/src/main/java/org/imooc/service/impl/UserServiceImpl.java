package org.imooc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.imooc.bean.User;
import org.imooc.dao.UserDao;
import org.imooc.dto.UserDto;
import org.imooc.service.UserService;
import org.imooc.util.CommonUtil;
import org.imooc.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public boolean validate(UserDto userDto) {
		if (userDto != null && !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			List<User> list = userDao.select(user);
			if(list.size() == 1) {
				BeanUtils.copyProperties(list.get(0),userDto);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public List<UserDto> getList() {
		List<UserDto> result = new ArrayList<>();
		List<User> userList = userDao.select(new User());
		for (User user : userList) {
			UserDto userDto = new UserDto();
			result.add(userDto);
			BeanUtils.copyProperties(user, userDto);
			userDto.setpId(0);
		}
		return result;
	}

	@Override
	public boolean add(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(MD5Util.getMD5(userDto.getPassword()));
		return userDao.insert(user) == 1;
	}

	@Override
	public UserDto getById(Long id) {
		UserDto userDto = new UserDto();
		User user = userDao.selectById(id);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public boolean modify(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		if(!CommonUtil.isEmpty(userDto.getPassword())) {
			user.setPassword(MD5Util.getMD5(userDto.getPassword()));
		}
		return userDao.update(user) == 1;
	}

	@Override
	public boolean remove(Long id) {
		return userDao.delete(id) == 1;
	}
}