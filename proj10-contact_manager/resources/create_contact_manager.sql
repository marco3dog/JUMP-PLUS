create database contacttracker;

use contacttracker;

create table user(
userid int primary key auto_increment,
email varchar(50) NOT NULL unique,
password varchar(50) NOT NULL 
);

create table contact(
contactid int primary key auto_increment,
userid int,
name varchar(50) NOT NULL,
email varchar(50) NOT NULL,
foreign key(userid) references user (userid)
);

