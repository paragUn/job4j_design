create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);


create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';


create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();


insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

select * from products;


insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

select * from products;

drop trigger discount_trigger on products;

---------------------------------
create or replace function after_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger after_tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure after_tax();

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 10, 100);
select * from products;


create or replace function data_insert()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values(
        new.name,
        new.price,
        CURRENT_TIMESTAMP
        );
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger before_tax_trigger
    before insert
    on products
    for each row
    execute procedure before_tax();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_62', 5, 120);
select * from products;
-------------------------------------------
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create or replace function data_insert()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values(
        new.name, 
        new.price,
        CURRENT_TIMESTAMP
        );
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger insert_price_history
    after insert
    on products
    for each row
    execute procedure data_insert();
    
insert into products (name, producer, count, price) VALUES ('product_10', 'producer_10', 5, 120);
insert into products (name, producer, count, price) VALUES ('product_10', 'producer_10', 5, 120);
insert into products (name, producer, count, price) VALUES ('product_10', 'producer_10', 5, 120);
insert into products (name, producer, count, price) VALUES ('product_10', 'producer_10', 5, 120);

select * from products;
select * from history_of_price;         
