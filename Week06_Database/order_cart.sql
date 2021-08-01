CREATE TABLE order_cart
(
    cart_id        INT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
    customer_id    INT UNSIGNED  NOT NULL COMMENT '用户ID',
    product_id     INT UNSIGNED  NOT NULL COMMENT '商品ID',
    product_amount INT           NOT NULL COMMENT '加入购物车商品数量',
    price          DECIMAL(8, 2) NOT NULL COMMENT '商品价格',
    add_time       TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入购物车时间',
    modified_time  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY pk_cartid (cart_id)
) ENGINE = innodb COMMENT '购物车表';