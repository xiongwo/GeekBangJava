CREATE TABLE product_info
(
    product_id        INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品ID',
    product_core      CHAR(16)                    NOT NULL COMMENT '商品编码',
    product_name      VARCHAR(20)                 NOT NULL COMMENT '商品名称',
    brand_id          INT UNSIGNED                NOT NULL COMMENT '品牌表的ID',
    one_category_id   SMALLINT UNSIGNED           NOT NULL COMMENT '一级分类ID',
    two_category_id   SMALLINT UNSIGNED           NOT NULL COMMENT '二级分类ID',
    three_category_id SMALLINT UNSIGNED           NOT NULL COMMENT '三级分类ID',
    price             DECIMAL(8, 2)               NOT NULL COMMENT '商品销售价格',
    publish_status    TINYINT                     NOT NULL DEFAULT 0 COMMENT '上下架状态：0下架1上架',
    audit_status      TINYINT                     NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核，1已审核',
    weight            FLOAT COMMENT '商品重量',
    length            FLOAT COMMENT '商品长度',
    height            FLOAT COMMENT '商品高度',
    width             FLOAT COMMENT '商品宽度',
    color_type        ENUM ('红','黄','蓝','黑'),
    production_date   DATETIME                    NOT NULL COMMENT '生产日期',
    shelf_life        INT                         NOT NULL COMMENT '商品有效期',
    descript          TEXT                        NOT NULL COMMENT '商品描述',
    indate            TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品录入时间',
    modified_time     TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY pk_productid (product_id)
) ENGINE = innodb COMMENT '商品信息表';