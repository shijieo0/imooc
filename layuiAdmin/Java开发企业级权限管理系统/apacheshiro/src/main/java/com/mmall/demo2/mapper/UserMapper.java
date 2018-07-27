package com.mmall.demo2.mapper;

import com.mmall.demo2.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
