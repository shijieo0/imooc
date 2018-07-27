package org.imooc.dto;

import org.imooc.bean.User;

public class UserDto extends User {
    
    private Integer pId;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}
}