use cart; 

create table if not exists product(
	product_id int auto_increment primary key comment '商品ID',
    product_name varchar(50) not null unique comment '商品名稱',
    price int not null comment '商品價格',
    qty int default 0 comment '商品庫存',
    image_base64 longtext comment '商品照片'
);