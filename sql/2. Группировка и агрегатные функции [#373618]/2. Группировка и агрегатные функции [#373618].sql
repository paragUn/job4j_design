create table devices(
    id serial primary key,
    name varchar(255),
    price FLOAT
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('iphone 14 e-sim 128', 85000.38);
insert into devices (name, price) values ('iphone 13 128', 64000.85);
insert into devices (name, price) values ('iphone 4s', 5000.00);

insert into people (name) values ('Аня'), ('Ваня'), ('Боря');

insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (2, 3), (2, 1), (3, 2); 

select avg(d.price) from devices d;

select p.name, avg(d.price) 
from devices_people as dp 
join devices d
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) 
from devices_people as dp 
join devices d
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 50000;
