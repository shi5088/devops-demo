package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 模拟数据库里的 User 对象
    static class User {
        public Long id;
        public String username;
        public String address; // 地址
    }

    // 漏洞1：SQL 注入
    @GetMapping("/search")
    public List<Map<String, Object>> searchUser(@RequestParam String username) {
        // AI 生成的错误代码：直接把 username 拼接到 SQL 里，存在严重的 SQL 注入风险
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        return jdbcTemplate.queryForList(sql);
    }

    // 漏洞2：空指针异常 (NPE)
    @GetMapping("/{id}/info")
    public String getUserInfo(@PathVariable Long id) {
        User user = new User();
        // 假设从数据库查到了 user，但是 address 为 null
        user.address = null;

        // AI 生成的错误代码：没有判断 address 是否为 null 就直接调用 .length()
        // 如果 address 为 null，这里会直接抛出 NullPointerException
        int addressLength = user.address.length();
        return "Address length: " + addressLength;
    }
}