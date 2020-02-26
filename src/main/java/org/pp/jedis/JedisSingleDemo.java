package org.pp.jedis;

import redis.clients.jedis.Jedis;

public class JedisSingleDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.131");
        jedis.auth("Redis!12345");

        String restaurant = "Pizza";
        jedis.set(restaurant, "300 Broadway, New York, NY");
        jedis.append(restaurant, "10011");
        String address = jedis.get("Pizza");
        System.out.printf("Address for %s is %s\n", restaurant, address);

        System.exit(0);
    }
}
