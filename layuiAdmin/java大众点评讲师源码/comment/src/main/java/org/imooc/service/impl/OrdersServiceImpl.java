package org.imooc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.imooc.bean.Orders;
import org.imooc.constant.CommentStateConst;
import org.imooc.dao.OrdersDao;
import org.imooc.dto.OrdersDto;
import org.imooc.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Resource
	private OrdersDao ordersDao;
	
	@Value("${businessImage.url}")
    private String businessImageUrl;

	@Override
	public boolean add(OrdersDto ordersDto) {
		Orders orders = new Orders();
		BeanUtils.copyProperties(ordersDto, orders);
		orders.setCommentState(CommentStateConst.NOT_COMMENT);
		ordersDao.insert(orders);
		return true;
	}

	@Override
	public OrdersDto getById(Long id) {
		OrdersDto result = new OrdersDto();
		Orders orders = ordersDao.selectById(id);
		BeanUtils.copyProperties(orders, result);
		return result;
	}

	@Override
	public List<OrdersDto> getListByMemberId(Long memberId) {
		List<OrdersDto> result = new ArrayList<OrdersDto>();
		Orders ordersForSelect = new Orders();
		ordersForSelect.setMemberId(memberId);
		List<Orders>  ordersList = ordersDao.select(ordersForSelect);
		for(Orders orders : ordersList) {
			OrdersDto ordersDto = new OrdersDto();
			result.add(ordersDto);
			BeanUtils.copyProperties(orders, ordersDto);
			ordersDto.setImg(businessImageUrl + orders.getBusiness().getImgFileName());
			ordersDto.setTitle(orders.getBusiness().getTitle());
			ordersDto.setCount(orders.getBusiness().getNumber());
		}
		return result;
	}
}