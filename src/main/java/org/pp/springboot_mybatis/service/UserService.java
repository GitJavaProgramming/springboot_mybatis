package org.pp.springboot_mybatis.service;

import org.pp.springboot_mybatis.entity.TestUser;
import org.pp.springboot_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;

    public TestUser Sel(int id) {
        return userMapper.Sel(id);
    }

    //    @Transactional(propagation = Propagation.REQUIRED)
    public int insertUser(TestUser user) {
        return userMapper.insertUser(user);
    }
}