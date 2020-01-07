package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface LoginService {
    public User User_query(String username);

    /**
     * 查询权限为privilege的所有用户的用户名
     * @param prvilege 需要查询的权限
     * @return 返回用户名
     */
    public List<String> query_username_according_to_privilege(int prvilege);

}
