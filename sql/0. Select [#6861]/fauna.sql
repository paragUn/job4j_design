create table fauna (
    id serial primary key,
    animal_name text,
    avg_age int,
    discovery_date date
);

insert into fauna(animal_name, avg_age, discovery_date) 
    values ('huso huso - fish', 100, '1758-01-01');
insert into fauna(animal_name, avg_age, discovery_date) 
    values ('Eunectes murinus - snake', 20, '1758-01-01');
insert into fauna(animal_name, avg_age, discovery_date) 
    values ('Cubozoa - medusa', 1, null);
insert into fauna(animal_name, avg_age, discovery_date) 
    values ('Delphinus delphis - dolphin', 25, '1758-01-01');
insert into fauna(animal_name, avg_age, discovery_date) 
    values ('Serrasalmus rhombeus - dolphin', 4, '1766-01-01');
insert into fauna(animal_name, avg_age, discovery_date) 
    values ('Panthera tigris- tiger', 18, '1758-01-01');

select * from fauna where animal_name like '%fish%';

select * from fauna where avg_age >= 10 AND avg_age <= 21;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';