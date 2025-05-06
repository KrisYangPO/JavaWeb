-- 使用者資料表: user
create table if not exists user (
    id int primary key auto_increment comment '自動生成編號',
    username varchar(50) not null unique comment '使用者名稱',
    hash_password varchar(255) not null comment '雜湊密碼',
    hash_salt varchar(255) not null comment '雜湊鹽',
    email varchar(255) not null comment 'email',
    image_base64 longtext comment '用戶照片',
    admintor boolean not null default false comment '管理員身份'
);