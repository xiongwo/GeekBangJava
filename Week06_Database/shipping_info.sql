CREATE TABLE shipping_info
(
    ship_id       TINYINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    ship_name     VARCHAR(20)      NOT NULL COMMENT '物流公司名称',
    ship_contact  VARCHAR(20)      NOT NULL COMMENT '物流公司联系人',
    telephone     VARCHAR(20)      NOT NULL COMMENT '物流公司联系电话',
    price         DECIMAL(8, 2)    NOT NULL DEFAULT 0.00 COMMENT '配送价格',
    modified_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY pk_shipid (ship_id)
) ENGINE = innodb COMMENT '物流公司信息表';