

drop database mockproject_t4_2022;

create database mockproject_t4_2022;

use mockproject_t4_2022;

create table roles(
	id int(11) primary key auto_increment,  
	name varchar(20) not null unique,
	description varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci unique,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now()
);

create table users(
	id int(11) primary key auto_increment,
	username varchar(20) not null unique,
	fullname varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci not null ,
	password varchar(255) not null,
	phone varchar(20) ,
	email varchar(100) not null unique,
	img_url varchar(255) null,
	is_deleted bit not null default 0, 
	created_at datetime not null default now(), 
	updated_at datetime not null default now()
);

create table user_roles(
	id int(11) primary key auto_increment,  
	user_id int(11) ,
	role_id int(11),
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now(),
	foreign key (user_id) references users(id),
	foreign key (role_id) references roles(id)
);

create table permission(
	id int(11) primary key auto_increment,  
	name varchar(20) not null unique,
	description varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci unique,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now()
);


create table user_permissions(
	id int(11) primary key auto_increment,  
	user_id int(11) ,
	permission_id int(11),
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now(),
	foreign key (user_id) references users(id),
	foreign key (permission_id) references permission(id)
);


create table product_types(
	id int(11) primary key auto_increment,
	description text CHARACTER SET utf8 COLLATE utf8_general_ci not null,
	slug varchar(255) not null,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now()
);

create table unit_types
(
	id int(11) primary key auto_increment,
	description text CHARACTER SET utf8 COLLATE utf8_general_ci not null,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now()
);

create table products(
	id int(11) primary key auto_increment,
	name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci not null,
	type_id int(11) ,
	quantity int not null,
	price decimal(20,0) not null,
	unit_id int(11) ,
	img_url varchar(255) null,
	description text CHARACTER SET utf8 COLLATE utf8_general_ci not null,
	slug varchar(255) not null unique,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now(),
	foreign key (type_id) references product_types(id),
	foreign key (unit_id) references unit_types(id)
);

create table orders(
	id int(11) primary key auto_increment,
	user_id int(11) ,
	address varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci not null,
	phone varchar(11) not null,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now(),
	foreign key (user_id) references users(id)
);

create table order_details(
	id int(11) primary key auto_increment,
	order_id int(11),
	product_id int(11) ,
	price decimal(20,0) not null,
	quantity int not null,
	is_deleted bit not null default 0,
	created_at datetime not null default now(), 
	updated_at datetime not null default now(),
	foreign key (order_id) references orders(id),
	foreign key (product_id) references products(id)
);

insert into roles(name,description) values
('OWENER','??ng ch???'),
('MANAGER','Qu???n l??'),
('ACCOUNTANT','K??? to??n'),
('EMPLOYEE SALES','Nh??n vi??n b??n h??ng'),
('CUSTOMER','Kh??ch h??ng');

-- duynt: pass 111
insert into users(username, fullname, password,phone, email, img_url) values
('duynt','Nguy???n Tr???n Duy', '$2a$10$bC3fIu4WyB/FGwlbOPlZt.3IRzkM34vZNt1Kbe5ZDcq7r/XZFWNrO','', 'duynt@abc.com', 'default.png');

insert into user_roles (user_id,role_id) values
(1,1);

insert into permission (name,description) values
('PASS_CHECKIN','Kh??ng c???n checkin'),
('INSERT','Th??m d??? li???u'),
('UPDATE','S???a d??? li???u'),
('DELETE','X??a d??? li???u'),
('WATCH_INFORMATION','Xem th??ng tin');

insert into user_permissions(user_id,permission_id) values
(1,1);

insert into product_types(description, slug) values
('??i???n tho???i', 'dien-thoai'),
('Laptop', 'laptop');

insert into unit_types(description) values
('Chi???c'),
('B???');

insert into products(name, type_id, quantity, price, unit_id, img_url, description, slug) values
('Iphone 11 64GB', 1, 5, 10000000, 1, 'iphone-11.jpg','??i???n tho???i Iphone 11 b???n 64GB', 'iphone-11-64gb'),
('Samsung A95', 1, 12, 7500000, 1, 'samsung-a95.jpg','<b>??i???n tho???i samsung A95</b> l?? m???u ??i???n tho???i m???i nh???t c???a Samsung v???i nhi???u t??nh n??ng v?????t tr???i', 'samsung-a95'),
('Laptop HP Pavilion i7 10th', 2, 1, 19250000, 1, 'laptop-hp-pavilion.jpg','Laptop HP Pavilion core i7 10th', 'laptop-hp-pavilion-i7-10th'),
('Laptop DELL Inspirion i5 gen 8', 2, 3, 13450000, 1, 'laptop-dell-inspirion.jpg','Laptop DELL Inspirion', 'laptop-dell-inspirion-i5-gen-8'),
('Iphone 12 Pro max 256GB', 1, 2, 37000000, 1, 'iphone-12-pro-max.jpg','Iphone x???n', 'iphone-12-pro-max-256gb'),
('Oppo Reno 4', 1, 15, 13270000, 1, 'oppo-reno-4.png','??i???n tho???i Oppo', 'oppo-reno-4'),
('San pham demo 1', 1, 15, 13270000, 1, 'oppo-reno-4.png','??i???n tho???i Oppo', 'san-pham-demo-1'),
('San pham demo 2', 1, 15, 13270000, 1, 'oppo-reno-4.png','??i???n tho???i Oppo', 'san-pham-demo-2'),
('San pham demo 3', 1, 15, 13270000, 1, 'oppo-reno-4.png','??i???n tho???i Oppo', 'san-pham-demo-3');



CALL sp__g_list_getTotalPricePerMonth('2022-01-20','2022-09-26',@a,@b);
SELECT @a,@b

CALL sp__g_list_getTotalPricePerMonth('2022-07-20','2022-09-26',@a,@b,@c);
SELECT @a,@b,@c

SELECT * FROM order_details od ;
SELECT * FROM orders o ;
SELECT * FROM products p ;

SELECT * FROM users u ;

SELECT * FROM roles r ;

CALL sp__g_list_getTotalPricePerMonth(@,@b);
SELECT * FROM @a,@b;









