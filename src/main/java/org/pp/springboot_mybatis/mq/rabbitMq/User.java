package org.pp.springboot_mybatis.mq.rabbitMq;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -1262627851741431084L;

    private String userId;

    private String name;
}
