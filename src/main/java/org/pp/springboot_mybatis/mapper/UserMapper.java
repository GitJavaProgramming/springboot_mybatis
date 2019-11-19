package org.pp.springboot_mybatis.mapper;

import org.pp.springboot_mybatis.entity.TestUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    TestUser Sel(int id);

    int insertUser(TestUser user);
}
