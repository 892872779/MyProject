package com.example.MyProject.service;

import com.example.MyProject.dto.LoginDTO;
import com.example.MyProject.entity.User;

public interface UserService {
    User login(LoginDTO loginDTO);

    Boolean register(LoginDTO loginDTO);
}
