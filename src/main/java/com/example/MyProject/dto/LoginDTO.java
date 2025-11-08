package com.example.MyProject.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;

    public String validate() {
        if (username == null || username.trim().isEmpty()) {
            return "用户名不能为空";
        }
        if (password == null || password.trim().isEmpty()) {
            return "密码不能为空";
        }
        return null; // 验证通过
    }
}