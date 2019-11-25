package org.pp.springboot_mybatis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.pp.springboot_mybatis.entity.TestUser;
import org.pp.springboot_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Resource
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

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
     * <p>
     * 1、ISOLOCATION_DEFAULT:  数据库默认级别
     * 2、ISOLOCATION_READ_UNCOMMITTED: 允许读取未提交的读， 可能导致脏读，不可重复读，幻读
     * 3、ISOLOCATION_READ_COMMITTED:  允许读取已提交的读，可能导致不可重复读，幻读
     * 4、ISOLOCATION_REPEATABLE_READ : 不能能更新另一个事务修改单尚未提交(回滚)的数据，可能引起幻读
     * 5、ISOLOCATION_SERIALIZABLE: 序列执行效率低
     * <p>
     * 传播级别：
     * <p>
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
        TestUser user = new TestUser();
        user.setUserName("un1");
        user.setPassWord("pw1");
        user.setRealName("rn1");
        userService.insertUser(user);
    }

    @Test
    void testJdbcTemplate() {
        String sql = "select * from user";
        List<TestUser> userList = jdbcTemplate.query(sql, new RowMapper<TestUser>() {
            @Override
            public TestUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                TestUser user = new TestUser();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("passWord"));
                user.setRealName(rs.getString("realName"));
                return user;
            }
        });
        System.out.println("查询成功：");
        for (TestUser user : userList) {
            System.out.println("id:" + user.getId() + ", name:" + user.getUserName());
        }
    }

    @Test
    void testRedisTemplate() {
        stringRedisTemplate.opsForValue().set("testEnv", "testEnvValue");
        String testEnv = stringRedisTemplate.opsForValue().get("testEnv");
        System.out.println(testEnv);
    }
}
