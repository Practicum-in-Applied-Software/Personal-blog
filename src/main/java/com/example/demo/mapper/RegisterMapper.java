package com.example.demo.mapper;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterMapper {
    @Insert("insert into user values (#{username},#{password},#{privilege},#{email},#{sex},#{phone})")
    public void user_insert(@Param("username")String username, @Param("password")String password,@Param("privilege") int privilege, @Param("email")String email, @Param("sex")String sex, @Param("phone")String phone);

}
