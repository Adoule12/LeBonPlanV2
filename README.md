# LeBonPlanV2

mysql --user root --password
create database LeBonPlanDB;
use LeBonPlanDB;
create table user(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, mail VARCHAR(320) UNIQUE NOT NULL, password VARCHAR(60), lastname VARCHAR(40), firstname VARCHAR(20), birthday DATE, phoneNumber INT UNIQUE NOT NULL, blacklisted INT DEFAULT 0 NOT NULL, grade INT DEFAULT 0 NOT NULL, picture VARCHAR(200));

create table listAd(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, title VARCHAR(50), price FLOAT NOT NULL,picture VARCHAR(200),description VARCHAR(512), city VARCHAR(50), owner int, category INT NOT NULL, state INT DEFAULT 0 NOT NULL, conditions INT NOT NULL, releaseDate DATE, FOREIGN KEY(owner) REFERENCES user(id) ON DELETE CASCADE);
