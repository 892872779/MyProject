package com.example.MyProject.mapper;

import com.example.MyProject.dto.LoginDTO;
import com.example.MyProject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("user") LoginDTO user);

    Integer insertUser(@Param("user") User user);
}
