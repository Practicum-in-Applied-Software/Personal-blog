package com.example.demo.mapper;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface LoginMapper {

    @Select("select * from user where user.username=#{username}")
    public User user_query(String username);

    /**
     * 查询权限为privilege的所有用户的用户名
     * @param prvilege 需要查询的权限
     * @return 返回用户名
     */
    @Select("select username from user where privilege=#{prvilege}")
    public List<String> query_username_according_to_privilege_mapper(int prvilege);
}
