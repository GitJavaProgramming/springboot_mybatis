package org.pp.springboot_mybatis.service;

import org.pp.springboot_mybatis.entity.User;
import org.pp.springboot_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User Sel(int id) {
        return userMapper.Sel(id);
    }

    //    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}