package org.imooc.dao;

import java.util.List;

import org.imooc.bean.GroupMenu;

public interface GroupMenuDao {
	
	/**
	 * 根据用户组主键，删除用户组与菜单之间的关联关系
	 * @param groupId 用户组主键
	 * @return 影响行数
	 */
	int deleteByGroupId(Long groupId);
	
	/**
	 * 批量新增
	 * @param list
	 * @return 影响行数
	 */
	int insertBatch(List<GroupMenu> list);
}