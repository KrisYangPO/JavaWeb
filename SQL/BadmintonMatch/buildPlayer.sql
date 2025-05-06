-- 基本球員資料表: player
use BadmintonMatch; 

create table if not exists player(
	player_id int auto_increment primary key comment '球員ID',
  user_id int not null comment '使用者',
  level int default 1 comment '羽球等級',
  FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE
);