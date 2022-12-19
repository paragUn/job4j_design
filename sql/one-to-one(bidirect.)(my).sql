create table customers(
	id serial primary key, 
	name varchar(255)
);

create table phones(
	id serial primary key,
	phone varchar(12) 
);

create table phones_customers(
	id serial primary key,
	phone_id int references phones(id) unique,
	customer_id int references customers(id) unique
);