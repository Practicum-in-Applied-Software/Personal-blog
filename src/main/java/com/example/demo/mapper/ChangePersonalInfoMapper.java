package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ChangePersonalInfoMapper {
    @Update("update user set password=#{password},email=#{email},sex=#{sex},phone=#{phone} where username=#{username}")
    public void change_user_info(@Param("password") String password,@Param("email") String email,@Param("sex") String sex,@Param("phone") String phone,@Param("username") String username);
}
