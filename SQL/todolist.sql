USE todolist;

create table if not exists todo(
	id int primary key auto_increment comment '序號',
    text varchar(50) not null unique comment '工作內容',
    completed boolean not null default false comment '是否完成'
);

