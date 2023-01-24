create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--drop table products;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 28, 220);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 80);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 98, 320);


select  * from products;


--read committed
begin trancsaction;
--1
select  * from products;

--2
select  * from products;

--1
insert into products (name, count, price) VALUES ('product_6', 11, 64);
delete from products where price = 220;
update products set price = 75 where name = 'product_1';
select  * from products;

--2
select  * from products;

--1
commit;

--2
select  * from products;

--1
abort;
--2
abort;

--repeatable read

select  * from products;

--1
begin transaction isolation level repeatable read;
--2
begin transaction isolation level repeatable read;

--1
select  * from products;
--2
select  * from products;

--1
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 7, 7);
delete from products where price = 80;
update products set price = 80 where name = 'product_1';

--2
update products set price = 75 where name = 'product_1';


--1
select  * from products;
--2
select  * from products;

--1
commit;

--serializable
--1
select  * from products;
--2
select  * from products;

--1
begin transaction isolation level serializable;
--2
begin transaction isolation level serializable;

--1
select sum(count) from products;
update products set count = 1000 where name = 'product_1';

--2
select sum(count) from products;
update products set count = 1002 where name = 'product_2';

--2
commit;
--1
commit;

























