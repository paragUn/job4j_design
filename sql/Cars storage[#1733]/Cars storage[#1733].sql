create table car_bodies(
     id serial primary key,
     cb_name varchar(30)
);

create table car_engines(
     id serial primary key,
     ce_name varchar(30)
);

create table car_transmissions(
     id serial primary key,
     ct_name varchar(30)
);

create table cars(
    id serial primary key,
    car_name varchar(30),
    cb_id int references car_bodies(id),
    ce_id int references car_engines(id),
    ct_id int references car_transmissions(id)
);

insert into car_bodies(cb_name) values 
    ('Hatchback'), 
    ('Station wagon'),
    ('Pickup truck'), 
    ('Sedan'), 
    ('Cabriolet');
    
select * from car_bodies;


insert into car_engines(ce_name) values 
    ('Gas'), 
    ('Diesel'), 
    ('Uranium-238');
    
select * from car_engines;


insert into car_transmissions(ct_name) values 
    ('Automatic'), 
    ('Manual'), 
    ('Robot');
    
select * from car_transmissions;


insert into cars(car_name, cb_id, ce_id, ct_id) values
    ('Car1', 4, 1, 1),
    ('Car2', 1, 2, 3),
    ('Car2', 5, 1, 1);
    
select * from cars;   

select c.id id, c.car_name name, cb.cb_name body_name, ce.ce_name engine, ct.ct_name transmission 
from cars c 
left join car_bodies cb on c.cb_id = cb.id
left join car_engines ce on c.ce_id = ce.id
left join car_transmissions ct on c.ct_id = ct.id;

select cb.cb_name body 
from cars c right join car_bodies cb on c.cb_id = cb.id
where c.id is null;

select ce.ce_name engine 
from cars c right join car_engines ce on c.ce_id = ce.id
where c.id is null;

select ct.ct_name transmission 
from cars c right join car_transmissions ct on c.ct_id = ct.id
where c.id is null;