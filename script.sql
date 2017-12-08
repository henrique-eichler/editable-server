create database editable;
create user editableuser identified by 'editablepass';
grant all privileges on editable.* to editableuser;

use editable;

drop table profile;
create table profile (
  id bigint auto_increment primary key,
  display_name varchar(200) not null,
  real_name varchar(200) not null,
  profile_picture binary,
  birthday date,
  gender varchar(100),
  ethnicity	varchar(100),
  religion varchar(100),
  height real,
  figure varchar(100),
  marital_status varchar(100),
  occupation varchar(250),
  about_me varchar(5000),
  city varchar(100),
  lat varchar(20),
  lon varchar(20)
);

insert into profile (display_name, real_name) values ('Henrique', 'Henrique Eichler');
insert into profile (display_name, real_name) values ('Carol', 'Anne Carolliny');
insert into profile (display_name, real_name) values ('Duda', 'Maria Eduarda');
insert into profile (display_name, real_name) values ('Piter', 'Pedro Henrique');
insert into profile (display_name, real_name) values ('Nega', 'Ana Luiza');
insert into profile (display_name, real_name) values ('Careca', 'Gabriel');
insert into profile (display_name, real_name) values ('Fred', 'Frederico');
insert into profile (display_name, real_name) values ('A', 'Alice');
insert into profile (display_name, real_name) values ('J', 'Julia');
commit;
