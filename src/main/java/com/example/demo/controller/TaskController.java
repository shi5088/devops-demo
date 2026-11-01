package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Task;
import com.example.demo.mapper.TaskMapper;
import jakarta.validation.Valid; // 核心注解
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/tasks")
    public String listTasks(@RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        Page<Task> page = new Page<>(pageNum, 2);
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0).orderByDesc("create_time");
        Page<Task> taskPage = taskMapper.selectPage(page, queryWrapper);
        model.addAttribute("page", taskPage);
        return "task-list";
    }

    // 1. 跳转到新增页面
    @GetMapping("/tasks/add")
    public String addPage(Model model) {
        model.addAttribute("task", new Task());
        return "task-add"; // 新建的页面
    }

    // 2. 处理新增表单提交（重点：@Valid 和 BindingResult）
    @PostMapping("/tasks/add")
    public String addTask(@Valid Task task, BindingResult result, Model model) {
        // 如果校验不通过（比如 title 为空）
        if (result.hasErrors()) {
            // 拿到第一个错误信息，存入 Model
            FieldError error = result.getFieldError();
            model.addAttribute("errorMsg", error != null ? error.getDefaultMessage() : "输入有误");
            // 带着原来的数据打回新增页面
            return "task-add";
        }
        // 校验通过，执行插入
        taskMapper.insert(task);
        return "redirect:/tasks"; // 重定向到列表页
    }
}