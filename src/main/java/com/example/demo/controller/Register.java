package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Register{
    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;
    @RequestMapping(value="/register")
    public String register(){
        return "/register";
    }

    @RequestMapping(value="/register_info",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register_info(@RequestParam("name") String name, @RequestParam("password") String pwd, @RequestParam("password_check") String pwd_check){
        Map<String,String> map=new HashMap<String,String>();
//        两次密码不一样
        if(pwd.equals(pwd_check))
        {
            List<User> user=loginService.User_query(name);
//        无同名用户
            if(user.size()==0)
            {
                map.put("num","1");
                int privilege=0;
                registerService.user_insert(name,pwd,privilege);
            }
            else
            {
                map.put("num","2");
            }
        }
        else
            map.put("num","3");
        return map;
    }
}

