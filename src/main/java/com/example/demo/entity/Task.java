package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank; // 注意是 jakarta，不是 javax
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Task {
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "任务标题不能为空")
    @Size(max = 50, message = "标题不能超过50个字")
    private String title;

    private Integer status;

    private LocalDateTime createTime;
}