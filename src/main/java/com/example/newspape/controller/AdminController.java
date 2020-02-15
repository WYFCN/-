package com.example.newspape.controller;

import com.example.newspape.bean.User;
import com.example.newspape.service.UserService;
import com.example.newspape.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public String userList(Model model)
    {
        List<User> users = userService.getAllUser();
        PageUtil pageInfo = new PageUtil(1, 9, users);
        model.addAttribute("userDTOList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "userList";
    }
    @GetMapping("/findUserList.do")
    public String findUserList(@RequestParam Map<String, String> map, Model model) throws Exception {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "0");
        }
        // 查询用户列表 及设置分页信息
        List<User> userDTOList = userService.getAllUser();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 9, userDTOList);
        model.addAttribute("userDTOList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "userList";
    }
}
