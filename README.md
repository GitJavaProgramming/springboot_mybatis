# springboot_mybatis

    springboot入门实战 各种技术整合:
    
        springboot默认数据源-->换成druid
        声明式事务
        mybatis整合
        mvc整合(RestFul,模板引擎等)
        redis整合
        actuator可视化管理整合
        RabbitMQ整合
        Kafka整合
        
        junit测试用例：
            org.pp.springboot_mybatis.SpringbootMybatisApplicationTests
        RestController测试：    
            org.pp.springboot_mybatis.controller.UserController
        
        sql文件:
          url: jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
          位置：sql/user.sql
        
        
    虚拟机系统：CentOS-7-x86_64-Minimal-1908.iso
        
        Redis：
        官网地址：https://redis.io/
          wget download.redis.io/releases/redis-5.0.7.tar.gz
          tar -zxf redis-5.0.7.tar.gz -C /usr/local
          cd /usr/local/redis-5.0.7
          make 
          make test
        
          Redis开机自启动：
        1、修改redis.conf文件，把daemonize no改成daemonize yes。
        
        2、redis自带的启动脚本$redis/utils/redis_init_script，$redis是redis的安装路径
            cp $redis/utils/redis_init_script /etc/init.d/redis
            
            cd /etc/init.d
            vi redis
            
            修改：
            REDISPORT=6379
            EXEC=/usr/local/redis-5.0.7/src/redis-server
            CLIEXEC=/usr/local/redis-5.0.7/src/redis-cli
        
            PIDFILE=/var/run/redis_${REDISPORT}.pid
            CONF="/usr/local/redis-5.0.7/redis.conf"
        
        3、修改文件权限：
            chmod +x /etc/init.d/redis
        
        4、脚本添加到系统服务列表：
            chkconfig --add redis
            chkconfig redis on
            chkconfig --list
        
        resources:/bak.yml  kafka配置备份    
