CREATE TABLE product_category
(
    category_id     SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '分类ID',
    category_name   VARCHAR(10)                      NOT NULL COMMENT '分类名称',
    category_code   VARCHAR(10)                      NOT NULL COMMENT '分类编码',
    parent_id       SMALLINT UNSIGNED                NOT NULL DEFAULT 0 COMMENT '父分类ID',
    category_level  TINYINT                          NOT NULL DEFAULT 1 COMMENT '分类层级',
    category_status TINYINT                          NOT NULL DEFAULT 1 COMMENT '分类状态',
    modified_time   TIMESTAMP                        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY pk_categoryid (category_id)
) ENGINE = innodb COMMENT '商品分类表';