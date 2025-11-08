package com.example.MyProject.controller;

import com.example.MyProject.common.Result;
import com.example.MyProject.dto.LoginDTO;
import com.example.MyProject.entity.User;
import com.example.MyProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private UserService userService;


    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录请求: {}", loginDTO.getUsername());
        try {
            User user = userService.login(loginDTO);
            if (user == null) {
                return Result.error("用户名或密码错误");
            }
            // 登录成功，返回用户信息（不包含密码）
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("username", user.getUsername());
            userInfo.put("email", user.getEmail());
            userInfo.put("phone", user.getPhone());
            log.info("用户登录成功: {}", loginDTO.getUsername());
            return Result.success(userInfo);

        } catch (Exception e) {
            log.error("用户登录异常: {}", loginDTO.getUsername(), e);
            return Result.error("登录失败，请稍后重试");
        }
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody LoginDTO loginDTO) {
        try {
            Boolean flag = userService.register(loginDTO);
            if (flag) {
                return Result.success("注册成功");
            } else {
                return Result.error("注册失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("用户注册异常: {}", loginDTO.getUsername(), e);
            return Result.error("注册失败，请稍后重试");
        }
    }

}