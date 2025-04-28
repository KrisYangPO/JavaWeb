use cart;
-- 使用者資料表
create table if not exists user (
    id int primary key auto_increment,
    username varchar(50) not null unique,
    hash_password varchar(255) not null comment '雜湊密碼',
    hash_salt varchar(255) not null comment '雜湊鹽',
    email varchar(255) not null,
    completed boolean not null default false
);

