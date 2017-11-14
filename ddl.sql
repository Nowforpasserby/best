create table city
(
	id bigint auto_increment
		primary key,
	name varchar(255) null,
	state varchar(255) null
)
comment '市级信息'
;

create table country
(
	Id int auto_increment comment '主键'
		primary key,
	country_name varchar(255) null comment '名称',
	country_code varchar(255) null comment '代码'
)
comment '国家信息'
;

create table user_info
(
	Id int auto_increment
		primary key,
	username varchar(32) default '' not null comment '用户名',
	password varchar(32) null comment '密码',
	user_type varchar(2) null comment '用户类型',
	enabled int(2) null comment '是否可用',
	real_name varchar(32) null comment '真实姓名',
	qq varchar(14) null comment 'QQ',
	email varchar(100) null,
	tel varchar(255) null comment '联系电话',
	city_id int null comment '城市编号'
)
comment '用户信息表'
;

