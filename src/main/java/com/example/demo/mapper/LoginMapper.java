package com.example.demo.mapper;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LoginMapper {
    @Select("select * from user where user.username=#{username}")

    public User user_query(String username);

}
