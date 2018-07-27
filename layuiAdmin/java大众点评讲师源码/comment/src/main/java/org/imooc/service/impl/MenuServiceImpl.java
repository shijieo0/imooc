package org.imooc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.imooc.bean.Action;
import org.imooc.bean.Menu;
import org.imooc.dao.ActionDao;
import org.imooc.dao.MenuDao;
import org.imooc.dto.MenuDto;
import org.imooc.dto.MenuForMoveDto;
import org.imooc.dto.MenuForZtreeDto;
import org.imooc.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ActionDao actionDao;

	@Override
	public List<MenuForZtreeDto> getZtreeList() {
		List<MenuForZtreeDto> result = new ArrayList<>();
		List<Menu> menuList = menuDao.selectWithAction(new Menu());
		for(Menu menu : menuList) {
			MenuForZtreeDto menuForZtreeDto = new MenuForZtreeDto();
			result.add(menuForZtreeDto);
			BeanUtils.copyProperties(menu, menuForZtreeDto);
			menuForZtreeDto.setOpen(true);
			menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU + menu.getId());
			menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + menu.getParentId());
			// 组装菜单下对应的动作
			for(Action action : menu.getActionList()) {
				menuForZtreeDto = new MenuForZtreeDto();
				result.add(menuForZtreeDto);
				menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_ACTION + action.getId());
				menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + action.getMenuId());
				menuForZtreeDto.setName(action.getName());
				menuForZtreeDto.setId(action.getId());
				menuForZtreeDto.setParentId(action.getMenuId());
			}
		}
		return result;
	}

	@Override
	public boolean add(MenuDto menuDto) {
		Menu menu = new Menu();
		BeanUtils.copyProperties(menuDto,menu);
		return menuDao.insert(menu) == 1;
	}

	@Override
	@Transactional
	public boolean remove(Long id) {
		actionDao.deleteByMenuId(id);
		return menuDao.delete(id) == 1;
	}

	@Override
	public MenuDto getById(Long id) {
		MenuDto result = new MenuDto();
		Menu menu = menuDao.selectById(id);
		BeanUtils.copyProperties(menu, result);
		return result;
	}

	@Override
	public boolean modify(MenuDto menuDto) {
		Menu menu = new Menu();
		BeanUtils.copyProperties(menuDto,menu);
		return menuDao.update(menu) == 1;
	}

	@Transactional
	@Override
	public boolean order(MenuForMoveDto menuForMoveDto) {
		// 如果移动到目标节点，成为目标节点的子节点
		if(MenuForMoveDto.MOVE_TYPE_INNER.equals(menuForMoveDto.getMoveType())) {
			// 先将目标节点下所有子节点排序数字+1（让出最前面的位置）
            menuDao.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());
            // 将移动的节点排序数字改为1，成为目标节点下最前面的子节点
            Menu menu = new Menu();
            menu.setOrderNum(1);
            menu.setId(menuForMoveDto.getDropNodeId());
            menu.setParentId(menuForMoveDto.getTargetNodeId());
            menuDao.update(menu);
        } else {
        	// 获取目标节点的排序数字
        	Menu menu = menuDao.selectById(menuForMoveDto.getTargetNodeId());
            if(menu != null) {
                // 如果移动到目标节点的前一个节点
                if(MenuForMoveDto.MOVE_TYPE_PREV.equals(menuForMoveDto.getMoveType())) {
                	// 将目标节点和目标节点后面的兄弟节点的排序数字加1
                	// （留出一个空位，也就是原本目标节点的位置）
                	menuDao.updateOrderNumByIdInclude(menuForMoveDto.getTargetNodeId());
                	
                	// 将移动的节点的排序数字更新为目标节点的原排序数字（排序到目标节点的前一个节点位置）
                	Menu menuForUpdate = new Menu();
                	menuForUpdate.setOrderNum(menu.getOrderNum());
                	menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                	menuForUpdate.setParentId(menu.getParentId());
                    menuDao.update(menuForUpdate);
                    
                } else if(MenuForMoveDto.MOVE_TYPE_NEXT.equals(menuForMoveDto.getMoveType())) {
                	// 如果移动到目标节点的后一个节点
                	
                	// 将目标节点后面的兄弟节点的排序数字加1（留出一个空位，也就是原本目标节点后面一个节点的位置）
                    menuDao.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());
                    // 将移动的节点的排序数字更新为目标节点的原排序数字加1（排到目标节点的后一个节点位置）
                    Menu menuForUpdate = new Menu();
                	menuForUpdate.setOrderNum(menu.getOrderNum() + 1);
                	menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                	menuForUpdate.setParentId(menu.getParentId());
                    menuDao.update(menuForUpdate);
                } else {
                	// 移动方式不可识别
                    return false;
                }
            } else {
            	// 目标节点已不存在
                return false;
            }
        }
        return true;
	}

	@Override
	public List<MenuDto> getList(MenuDto menuDto) {
		List<MenuDto> result = new ArrayList<>();
		Menu menuForSelect = new Menu();
		BeanUtils.copyProperties(menuDto, menuForSelect);
		List<Menu> menuList = menuDao.select(menuForSelect);
		for(Menu menu : menuList) {
			MenuDto menuDtoTemp = new MenuDto();
			result.add(menuDtoTemp);
			BeanUtils.copyProperties(menu, menuDtoTemp);
		}
		return result;
	}	
}
