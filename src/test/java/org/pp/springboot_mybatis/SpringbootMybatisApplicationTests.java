package org.pp.springboot_mybatis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.pp.springboot_mybatis.entity.User;
import org.pp.springboot_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Resource
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Test
    void loadDataSource() throws SQLException {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();

        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        connection.close();
    }

    /**
     * 隔离级别：
     *
     * 1、ISOLOCATION_DEFAULT:  数据库默认级别
     * 2、ISOLOCATION_READ_UNCOMMITTED: 允许读取未提交的读， 可能导致脏读，不可重复读，幻读
     * 3、ISOLOCATION_READ_COMMITTED:  允许读取已提交的读，可能导致不可重复读，幻读
     * 4、ISOLOCATION_REPEATABLE_READ : 不能能更新另一个事务修改单尚未提交(回滚)的数据，可能引起幻读
     * 5、ISOLOCATION_SERIALIZABLE: 序列执行效率低
     *
     * 传播级别：
     *
     * 1、PROPERGATION_MANDATORY:　方法必须运行在一个事务中，不存在事务则抛出异常
     * 2、PROPERGATION_NESTED:　　存在事务则运行在嵌套事务中，不存在则创建一个事务
     * 3、PROPERGATION_NEVER: 当前方法不能运行在事务中，存在事务则抛出异常
     * 4、PROPERGATION_NOT_SUPPORT: 当前存在事务则将其 挂起
     * 5、PROPERGATION_REQUIRED: 不存在事务则创建一个事务
     * 6、PROPERGATION_REQUIRES_NEW:  新建一个自己的事务，不论当前是否存在事务
     * 7、PROPERGATION_SUPPORT: 存在事务则加入，不存在也可以
     */
    @Test
    @Transactional
//    @Rollback(false)
    void validateTransactional() {
        User user = new User();
        user.setUserName("un1");
        user.setPassWord("pw1");
        user.setRealName("rn1");
        userService.insertUser(user);
    }

}
