package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Login{
    @Autowired
    private LoginService loginService;
    //method表示post方法，也可以换成method方法

    @RequestMapping(value="/")
    public String login(){
        return "index";
    }
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String check(@RequestParam("form-username") String name, @RequestParam("form-password") String pwd , HttpServletResponse response){
        List<User> user=loginService.User_query(name);
        if (user.size()==0)
            return "error";
        else
        {
            if(user.get(0).getPwd().equals(pwd))
            {
                Cookie username=new Cookie("username",name);
                Cookie privilege=new Cookie("privilege",user.get(0).getPrivilege());
                response.addCookie(username);
                response.addCookie(privilege);
                return "success";
            }
            else
                return "error";
        }
    }
}

