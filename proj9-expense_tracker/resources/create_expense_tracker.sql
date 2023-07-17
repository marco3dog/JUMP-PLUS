create database expensetracker;

use expensetracker;

create table user(
userid int primary key auto_increment,
username varchar(50) NOT NULL unique,
password varchar(50) NOT NULL 
);

create table account(
accountid int primary key auto_increment,
userid int,
name varchar(50) NOT NULL,
monthlyBudget double CHECK (monthlyBudget > 0),
foreign key(userid) references user (userid)
);

create table expense(
expenseid int primary key auto_increment,
accountid int,
name varchar(50) NOT NULL,
monthlyCost double CHECK (monthlyCost > 0),
foreign key(accountid) references account (accountid)
);