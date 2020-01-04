package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */


@Controller
public class Index {


    @RequestMapping("/index")
    public String index(){

        return "demo1/index";
    }


    @RequestMapping("/night_index")
    public String night_index(){


        return "demo2/index";
    }

}
