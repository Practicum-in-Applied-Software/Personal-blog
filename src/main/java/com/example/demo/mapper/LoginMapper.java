package com.example.demo.mapper;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;
@Mapper
public interface LoginMapper {
    //查询学号为student_number的同学的选课情况
    @Select("select * from user where user.username=#{username}")

    public List<User> user_query(String username);

}
