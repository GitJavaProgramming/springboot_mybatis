# Redis 两小时入门
    redis是由C语言编写的，key-value形式的存储系统（映射-数学模型）。
## 准备工作
    redis安装（本例linux centos7 minimal下安装 设置自启动）
## 使用redis
    ./redis-cli进入redis客户端，如果redis服务器设置密码，那么在使用前需要验证密码：auth Redis!12345
    映射 y=f(x). (x={n})
### redis的键与值
    FAQ. redis中的键是什么？？什么数据类型？？
    值类型：String|Hash|List|Set
    数据类型命令简介：
        插入数据
        set s_key s_value
        hset hash_key h1 h1_value
        hset hash_key h2 h2_value
        查询所有key
        127.0.0.1:6379> keys *
        1) "s_key"
        2) "hash_key"
        获取在哈希表中指定key的所有字段和值
        127.0.0.1:6379> hgetall hash_key
        1) "h1"
        2) "h1_value"
        3) "h2"
        4) "h2_value"
        查询键位hash_key的Hash类型的所有值
        127.0.0.1:6379> hvals hash_key
        1) "h1_value"
        2) "h2_value"
        操作列表
        lpush list_key hello
        linsert list_key before hello world
        lset list_key 1 pengpeng
        lrange list_key 0 -1   # -1 表示列表的最后一个元素
### redis数据库事务 
    定义--参考wiki https://zh.wikipedia.org/wiki/数据库事务
    数据库事务（简称：事务）是数据库管理系统执行过程中的一个逻辑单位，由一个有限的数据库操作序列构成。
    用途
    数据库事务通常包含了一个序列的对数据库的读/写操作。包含有以下两个目的：
    为数据库操作序列提供了一个从失败中恢复到正常状态的方法，同时提供了数据库即使在异常状态下仍能保持一致性的方法。
    当多个应用程序在并发访问数据库时，可以在这些应用程序之间提供一个隔离方法，以防止彼此的操作互相干扰。
    相关命令
    multi  标记事务块开始
    exec 执行事务块
    discard 取消事务
    watch key [key...] 监视一个或多个key，如果在事务执行之前这个(或这些)key被其他命令所改动，那么事务将被打断。
    unwatch 取消watch命令对有所key的监视
    
    127.0.0.1:6379> multi
    OK
    127.0.0.1:6379> set book-name "Java programming"
    QUEUED
    127.0.0.1:6379> get book-book
    QUEUED
    127.0.0.1:6379> sadd tag "Java" "programming"
    QUEUED
    127.0.0.1:6379> smembers tag
    QUEUED
    127.0.0.1:6379> exec
    1) OK
    2) (nil)
    3) (integer) 2
    4) 1) "programming"
       2) "Java"
    127.0.0.1:6379>
    
    其他命令
    expire 设置一个键过期时间
    sort 自然排序  sort list_key alpha
### redis与lua脚本 
    脚本使redis更易使用
    使用脚本
    Redis 脚本使用 Lua 解释器来执行脚本。 Redis 2.6 版本通过内嵌支持 Lua 环境。执行脚本的常用命令为 EVAL。 
    eval "return redis.call('set', KEYS[1], ARGV[1])" 1 s_key foo
    get s_key
### redis持久化
    持久化：将数据持久保存在存储设备以待使用的方式
    redis持久化方式：RDB、AOF
    RDB持久化（原理是将Reids在内存中的数据库记录定时dump到磁盘上的RDB持久化）
    AOF持久化（原理是将Reids的操作日志以追加的方式写入文件）
### redis集群   
    
## 参考书籍
    https://www.runoob.com/redis/redis-commands.html
    redis入门指南 第二版
    redis设计与实现
    Redis深度历险：核心原理和应用实践