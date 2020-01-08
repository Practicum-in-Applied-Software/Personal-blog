package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

//    @RequestMapping(value="/register_info",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> register_info(HttpServletResponse resp,@RequestParam("name") String name, @RequestParam("password") String pwd, @RequestParam("password_check") String pwd_check, @RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("sex") String sex)throws Exception{
//        Map<String,String> map=new HashMap<String,String>();
//        System.out.println(name);
//        System.out.println(phone);
//        System.out.println(email);
//        System.out.println(sex);
//        System.out.println(pwd);
//        System.out.println(pwd_check);
////        表单项非空验证
//        if(phone.length()==0||email.length()==0||name.length()==0||sex.length()==0||pwd.length()==0||pwd_check.length()==0)
//        {
//            map.put("num","7");
//            return map;
//        }
////        电话号码格式验证
//        if(phone.length()!=11)
//        {
//            map.put("num","4");
//            return map;
//        }
////        简单邮箱格式验证
//        if(!email.contains("@"))
//        {
//            map.put("num","5");
//            return map;
//        }
////        性别验证
//        if(!sex.equals("boy")&&!sex.equals("girl"))
//        {
//            map.put("num","6");
//            return map;
//        }
////        两次密码不一样
//        if(pwd.equals(pwd_check))
//        {
//
//            User user=loginService.User_query(name);
////        无同名用户
//            if(user==null)
//            {
//                map.put("num","1");
//                int privilege=0;
//                registerService.user_insert(name,pwd,privilege,email,sex,phone);
//                System.out.println(map);
//                return map;
//            }
//            else
//            {
//                map.put("num","2");
//                return map;
//            }
//        }
//        else if(!pwd.equals(pwd_check))
//            map.put("num","3");
//        System.out.println(map);
//        return map;
//    }
@RequestMapping(value="/register_info",method = RequestMethod.POST)
public String register_info(@RequestParam("username") String name, @RequestParam("psw") String pwd, @RequestParam("psw_check") String pwd_check, @RequestParam("usrtel") String phone, @RequestParam("email") String email, @RequestParam("sex") String sex){
    Map<String,String> map=new HashMap<String,String>();
    System.out.println(name);
    System.out.println(phone);
    System.out.println(email);
    System.out.println(sex);
    System.out.println(pwd);
    System.out.println(pwd_check);
//        表单项非空验证
    if(phone.length()==0||email.length()==0||name.length()==0||sex.length()==0||pwd.length()==0||pwd_check.length()==0)
    {
        map.put("num","7");
        return "redirect:/register?7";
    }
//        电话号码格式验证
    if(phone.length()!=11)
    {
        map.put("num","4");
        return "redirect:/register?4";
    }
//        简单邮箱格式验证
    if(!email.contains("@"))
    {
        map.put("num","5");
        return "redirect:/register?5";
    }
//        性别验证
    if(!sex.equals("boy")&&!sex.equals("girl"))
    {
        map.put("num","6");
        return "redirect:/register?6";
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
            System.out.println(map);
            return "redirect:/login";
        }
        else
        {
            map.put("num","2");
            return "redirect:/register?2";
        }
    }
    else if(!pwd.equals(pwd_check))
        map.put("num","3");
    return "redirect:/register?3";
    }
}

