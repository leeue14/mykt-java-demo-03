package com.leeue.mapper;

import com.leeue.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM  user_info ")
    List<UserEntity> findUser();

    @Select("insert into user_info values (#{userName}); ")
    List<UserEntity> insertUser(@Param("userName") String userName);
}
