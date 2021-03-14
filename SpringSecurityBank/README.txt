To use JdbcUserDetailsManager, the setup is:

a) Create a DB. In this case, I used AWS RDS and created a MySQL instance - don't forget to make it publicly available.

b) Create "users" and "authorities" tables (to go with expected values in JdbcUserDetailsManager implementation):
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

c) Insert default user:
	insert ignore into users values (null, 'happy', '12345', '1');
	insert ignore into authorities values (null, 'happy', 'write');

c) Add the following properties in application.properties:
	spring.datasource.url=URL
	spring.datasource.username=USERNAME
	spring.datasource.password