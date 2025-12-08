-- 1. 创建数据库（不存在则创建），指定字符集为utf8mb4（支持中文/Emoji），排序规则适配中文
CREATE DATABASE IF NOT EXISTS hengxiaoliang
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 2. 切换到目标数据库
USE hengxiaoliang;

-- 3. 创建系统用户表 t_system_user（字段与User实体一一对应）
DROP TABLE IF EXISTS t_system_user;
CREATE TABLE t_system_user (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID（主键）',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    age INT NULL COMMENT '年龄',
    description VARCHAR(200) NULL COMMENT '用户描述',
    PRIMARY KEY (id) COMMENT '主键索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 4. 插入3条测试数据（简单且符合字段规则）
INSERT INTO t_system_user (username, age, description) VALUES
('admin', 30, '系统管理员，拥有最高权限'),
('zhangsan', 25, '普通业务用户，负责订单管理'),
('lisi', 28, '测试用户，用于功能验证');


-- 5. 创建普通用户表 t_user（字段与User实体一一对应）
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID（主键）',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    age INT NULL COMMENT '年龄',
    description VARCHAR(200) NULL COMMENT '用户描述',
    PRIMARY KEY (id) COMMENT '主键索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='普通用户表';

-- 6. 插入3条测试数据（简单且符合字段规则）
INSERT INTO t_user (username, age, description) VALUES
('wangwu', 31, '卖王八的用户'),
('zhaoliu', 23, '普通用户，无业游民'),
('tianqi', 25, '卖鸡肉的');