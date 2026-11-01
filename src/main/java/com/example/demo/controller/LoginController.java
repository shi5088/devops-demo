package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 需要建一个 login.html
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // 简单硬编码测试：账号 admin，密码 123456
        if ("admin".equals(username) && "123456".equals(password)) {
            session.setAttribute("user", username); // 登录成功，存入 session
            return "redirect:/tasks";
        }
        return "redirect:/login?error"; // 登录失败
    }
}