CREATE TABLE customer_inf
(
    customer_inf_id    INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
    customer_id        INT UNSIGNED                NOT NULL COMMENT 'customer_login表的自增ID',
    customer_name      VARCHAR(20)                 NOT NULL COMMENT '用户真实姓名',
    identity_card_type TINYINT                     NOT NULL DEFAULT 1 COMMENT '证件类型：1 身份证，2 军官证，3 护照',
    identity_card_no   VARCHAR(20) COMMENT '证件号码',
    mobile_phone       INT UNSIGNED COMMENT '手机号',
    customer_email     VARCHAR(50) COMMENT '邮箱',
    gender             CHAR(1) COMMENT '性别',
    register_time      TIMESTAMP                   NOT NULL COMMENT '注册时间',
    modified_time      TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY pk_customerinfid (customer_inf_id)
) ENGINE = innodb COMMENT '用户信息表';