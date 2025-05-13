-- 球隊球員列表資料表: teamplayer
use BadmintonMatch; 

create table if not exists teamplayer (
  id int auto_increment primary key comment '名冊 ID',
  team_id int not null comment '球隊 ID',
  player_id int not null comment '球員 ID',
  win_rate double default 0.0 comment '球員勝率',
  win_game int default 0 comment '勝場',
  total_match int default 0 comment '總場次',
  play_date datetime NOT NULL default current_timestamp COMMENT '入隊日期',
  FOREIGN KEY (team_id) REFERENCES `team`(team_id) ON DELETE CASCADE,
  FOREIGN KEY (player_id) REFERENCES `player`(pplayerlayer_id) ON DELETE CASCADE
);