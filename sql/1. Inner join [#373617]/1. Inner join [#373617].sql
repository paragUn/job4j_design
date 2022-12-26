create table flats(
    id serial primary key,
    number int
);

create table owners(
    id serial primary key,
    owner_name varchar(255),
    number_id int references flats(id) unique
);

insert into flats(number) values (100);
insert into flats(number) values (200);
insert into flats(number) values (300);
insert into flats(number) values (400);
insert into flats(number) values (500);

insert into owners (owner_name, number_id) values ('Ivanova', 1);
insert into owners (owner_name, number_id) values ('Petrova', 2);
insert into owners (owner_name, number_id) values ('Sidorova', 3);
insert into owners (owner_name, number_id) values ('Petrenko', 4);
insert into owners (owner_name, number_id) values ('Ignatenko', 5);

select o.owner_name as Владелец, f.number as Номер
from
owners as o join flats as f on o.number_id = f.id;