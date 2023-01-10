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
    ('Bmw', 5, 1, 1),
    ('Mersedes', 1, 2, 3),
    ('Bmw', 5, 1, 1);
    
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

create view cars_with_2_same_transmission_or_more as
    select c.car_name name, ct.ct_name transmission, count(ct.ct_name), cb.cb_name body, ce.ce_name engine
    from cars c 
         join car_bodies cb on c.cb_id = cb.id
         join car_engines ce on c.ce_id = ce.id
         join car_transmissions ct on c.ct_id = ct.id
    group by (c.car_name, ct.ct_name, cb.cb_name, ce.ce_name) 
    having count(ct.ct_name) > 1;

select * from cars_with_2_same_transmission_or_more;