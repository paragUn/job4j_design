create table type(
    id serial primary key, 
    name varchar(30)
);

create table product(
    id serial primary key,
    name varchar(30),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price)
    values ('Сыр Моцарелла', 1, '2022-12-20', 189.90);
insert into product(name, type_id, expired_date, price)
    values ('Сыр Плавленый', 1, '2022-12-31', 100.94);
insert into product(name, type_id, expired_date, price)
    values ('Сыр Гауда', 1, '2023-01-31', 220.11);
insert into product(name, type_id, expired_date, price)
    values ('Молоко безлактозное', 2, '2024-12-31', 120.50);
insert into product(name, type_id, expired_date, price)
    values ('Молоко 3,5%', 2, '2022-12-25', 98.33);
insert into product(name, type_id, expired_date, price)
    values ('Молоко 3,5%', 2, '2022-12-25', 95);
insert into product(name, type_id, expired_date, price)
    values ('Молоко 3,5%', 2, '2022-12-25', 108);
insert into product(name, type_id, expired_date, price)
    values ('Молоко топленое', 2, '2022-12-01', 100);
insert into product(name, type_id, expired_date, price)
    values ('Мороженое пломбир шоколадный', 3, '2022-12-10', 180.11);
insert into product(name, type_id, expired_date, price)
    values ('Мороженое пломбир клубничный', 3, '2022-12-31', 190);
insert into product(name, type_id, expired_date, price)
    values ('Мороженое пломбир сливочный', 3, '2023-02-08', 194);

select p.name from product as p
    join type
        on p.type_id = type.id
where type.name = 'СЫР';

select p.name from product as p
where name like '%ороженое%';

select * from product
where expired_date <= current_date;

select name, MAX(price) as max_price from product
group by name, price
where price = MAX(price);

select type.name, COUNT(product.name) as Количество from product
    join type
        on type.id = product.type_id
group by type.name;

select product.name from product
    join type
        on product.type_id = type.id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

select type.name, COUNT(product.name) as Количество from product
    join type
        on type.id = product.type_id
group by type.name
having count(product.name) < 10;

select product.name, type.name as Тип from product
    join type
        on type.id = product.type_id;

