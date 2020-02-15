package com.example.newspape.controller;

import com.example.newspape.bean.User;
import com.example.newspape.service.UserService;
import com.example.newspape.util.PageUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class PracticeController {
    @Autowired
    UserService userService;
    @GetMapping("/hello")
    public String tt(Model model) {
        System.out.println("sssssssss");
        List<User> userDTOList = userService.getAllUser();
        // 参数为当前页码、每页显示条数、查询的列表集合
        for (User user : userDTOList) {
            System.out.println(user);
        }
        PageUtil pageInfo = new PageUtil(1, 5, userDTOList);
        model.addAttribute("userDTOList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "practice";
    }

    @RequestMapping("/findUserList.do")
    public String findUserList(@RequestParam Map<String, String> map, Model model) throws Exception {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "0");
        }
        // 查询用户列表 及设置分页信息
        List<User> userDTOList = userService.getAllUser();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 5, userDTOList);
        model.addAttribute("userDTOList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "practice";
    }

}
