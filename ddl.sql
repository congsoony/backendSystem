use connectdb;
drop table if exists carddb;
create table carddb(
cardnum int auto_increment primary key,
name varchar(255),
phone varchar(255) unique,
company_name varchar(255),
create_date datetime
);
