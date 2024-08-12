create table users (
id int not null primary key auto_increment,
email varchar(32) not null unique,
password varchar(32) not null,
phone_number varchar(32) unique,
name varchar(32),
role tinyint
);