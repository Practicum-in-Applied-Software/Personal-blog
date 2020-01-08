package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.CookiesService;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class Login{

    @Autowired
    private LoginService loginService;
    //method表示post方法，也可以换成method方法

    @Autowired
    private CookiesService cookiesService;

    @RequestMapping("/login")
    public String login(){
        return "login_register/index";
    }

    @RequestMapping("/logout")
    public String logout(){

        cookiesService.deleteCookies("username");
        cookiesService.deleteCookies("privilege");
        cookiesService.deleteCookies("pwd");

        return "redirect:/login";
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String check(@RequestParam("form-username") String name, @RequestParam("form-password") String pwd){
        User user=loginService.User_query(name);

        if (user==null)
            return "redirect:/login";
        else
        {
            if(user.getPwd().equals(pwd))
            {
                cookiesService.setCookies("username",name);
                cookiesService.setCookies("privilege",String.valueOf(user.getPrivilege()));
                cookiesService.setCookies("pwd",pwd);
                return "redirect:/index";
            }
            else
                return "redirect:/error_page";
        }
    }

    @RequestMapping("/error_page")
    public String error(){
        return "login_register/error";
    }
}

