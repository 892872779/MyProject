package com.example.MyProject.service.impl;

import com.example.MyProject.dto.LoginDTO;
import com.example.MyProject.entity.User;
import com.example.MyProject.mapper.UserMapper;
import com.example.MyProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO loginDTO) {
        // 根据用户名查询用户
        User user = userMapper.findByUsername(loginDTO.getUsername());
        // 用户不存在
        if (user == null) {
            log.info("用户不存在: {}", loginDTO.getUsername());
            return null;
        } else {
            return user;
        }
    }
}
