For this project, simply create a DB using AWS RDS MySQL - don't forget to make it publicly available.

********************************************************************************
                          Using JdbcUserDetailsManager
********************************************************************************
To use JdbcUserDetailsManager, the setup is:

a) Create "users" and "authorities" tables (to go with expected values in JdbcUserDetailsManager implementation):
	create table users (
		id int not null auto_increment,
		username varchar(45) not null,
		password varchar(45) not null,
		enabled int not null,
		primary key(id)
	);

	create table authorities (
		id int not null auto_increment,
		username varchar(45) not null,
		authority varchar(45) not null,
		primary key(id)
	);

b) Insert default user:
	insert ignore into users values (null, 'happy', '12345', '1');
	insert ignore into authorities values (null, 'happy', 'write');

c) Add the following properties in application.properties:
	spring.datasource.url=URL
	spring.datasource.username=USERNAME
	spring.datasource.password=PASSWORD

********************************************************************************
               Custom Implementation of UserDetailsService
********************************************************************************
For the custom implementation of UserDetailsService, this is the setup is:

a) Create the table where you will have user details. In this case: 

	create table customer (
		id int not null auto_increment,
		email varchar(45) not null,
		password varchar(45) not null,
		role varchar(45) not null,
		primary key(id)
	);

b) Insert default user:
	insert ignore into customer values (null, 'test@test.com', '123', 'admin');