use BadmintonMatch; 

create table if not exists team (
  team_id int auto_increment primary key comment '球隊ID',
  team_name varchar(50) not null unique comment '球隊名稱',
  avg_level int comment '球隊平均等級',
  team_mem int comment '球隊人數',
  team_place varchar(50) not null unique comment '球隊地址',
  recruit boolean not null comment '招募球員',
  cap_id int not null comment '隊長編號',
  FOREIGN KEY (cap_id) REFERENCES `player`(player_id) ON DELETE CASCADE
);