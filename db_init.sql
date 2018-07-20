CREATE DATABASE javabase DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'java'@'localhost' IDENTIFIED BY 'd$7hF_r!9Y';
GRANT ALL ON javabase.* TO 'java'@'localhost' IDENTIFIED BY 'd$7hF_r!9Y';
use javabase;
CREATE TABLE IF NOT EXISTS tetris_scores (
	name VARCHAR(255),
	score BIGINT 	
);
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=1000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=2000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=3000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=4000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=5000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=6000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=7000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=8000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=9000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=10000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=11000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=12000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=13000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=14000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=15000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=16000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=17000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=18000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=19000;
INSERT INTO tetris_scores SET `name` = 'Anonimus', `score`=20000;
