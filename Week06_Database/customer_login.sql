CREATE TABLE customer_login
(
    customer_id   INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '用户ID',
    login_name    VARCHAR(20)                 NOT NULL COMMENT '用户登录名',
    password      CHAR(32)                    NOT NULL COMMENT 'md5加密的密码',
    user_stats    TINYINT                     NOT NULL DEFAULT 1 COMMENT '用户状态',
    modified_time TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY pk_customerid (customer_id)
) ENGINE = innodb COMMENT '用户登录表';