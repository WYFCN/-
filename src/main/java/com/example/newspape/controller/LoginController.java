package com.example.newspape.controller;

import com.example.newspape.bean.Order;
import com.example.newspape.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @ResponseBody
    @GetMapping("/toPra")
    public String ta(HttpServletRequest res)
    {
        System.out.println("##################");
        String loginUser = (String) res.getSession().getAttribute("loginUser");
        loginService.updateLogin(loginUser);
        return "succe";
    }
}
