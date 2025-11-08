package com.example.MyProject.service.impl;

import com.example.MyProject.dto.LoginDTO;
import com.example.MyProject.entity.User;
import com.example.MyProject.mapper.UserMapper;
import com.example.MyProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO loginDTO) {
        // 密码加密验证（这里使用简单的MD5加密，实际项目中建议使用更安全的加密方式）
        String encryptedPassword = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        loginDTO.setPassword(encryptedPassword);
        // 根据用户名查询用户
        User user = userMapper.findByUsername(loginDTO);
        // 用户不存在
        if (user == null) {
            log.info("用户不存在: {}", loginDTO.getUsername());
            return null;
        }
        return user;
    }

    @Override
    public Boolean register(LoginDTO loginDTO) {
        // 密码加密验证（这里使用简单的MD5加密，实际项目中建议使用更安全的加密方式）
        String encryptedPassword = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(encryptedPassword);
        user.setEmail(loginDTO.getEmail());
        user.setPhone(loginDTO.getPhone());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(1);
        Integer i = userMapper.insertUser(user);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
