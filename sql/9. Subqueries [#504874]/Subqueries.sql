CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values('Ivan', 'Petrov', 10, 'Russia');
insert into customers(first_name, last_name, age, country) values('John', 'Smith', 19, 'USA');
insert into customers(first_name, last_name, age, country) values('Pedro', 'Lopes', 50, 'Chile');
insert into customers(first_name, last_name, age, country) values('Petr', 'Ivanov', 99, 'Russia');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id) values (5, 1);
insert into orders (amount, customer_id) values (10, 2);
insert into orders (amount, customer_id) values (15, 3);

select * from customers where customers.id NOT IN (select customer_id from orders);
