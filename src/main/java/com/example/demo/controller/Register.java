package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Register{
    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;


    @RequestMapping(value="/register")
    public String register(){
        return "login_register/register";
    }

    @RequestMapping(value="/register_info",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register_info(@RequestParam("name") String name, @RequestParam("password") String pwd, @RequestParam("password_check") String pwd_check,@RequestParam("phone") String phone,@RequestParam("email") String email,@RequestParam("sex") String sex){
        Map<String,String> map=new HashMap<String,String>();
        System.out.println(phone);
        System.out.println(email);
        System.out.println(sex);
//        电话号码格式验证
        if(phone.length()!=11)
        {
            map.put("num","4");
            return map;
        }
//        简单邮箱格式验证
        if(!email.contains("@"))
        {
            map.put("num","5");
            return map;
        }
//        性别验证
        if(!sex.equals("boy")||!sex.equals("girl"))
        {
            map.put("num","6");
            return map;
        }
//        两次密码不一样
        if(pwd.equals(pwd_check))
        {
            User user=loginService.User_query(name);
//        无同名用户
            if(user==null)
            {
                map.put("num","1");
                int privilege=0;
                registerService.user_insert(name,pwd,privilege,email,sex,phone);
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

