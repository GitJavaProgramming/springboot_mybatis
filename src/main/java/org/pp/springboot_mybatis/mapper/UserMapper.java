package org.pp.springboot_mybatis.mapper;

import org.pp.springboot_mybatis.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(int id);

    int insertUser(User user);
}
