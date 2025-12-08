package com.hxl.deploy.domain.entity;

import lombok.Data;

/**
 * 系统用户
 *
 * @author hengxiaoliang
 */
@Data
public class User {

    private Long id;

    private String username;

    private Integer age;

    private String description;
}
