create table owners(
    id serial primary key,
    name varchar(255)
);

create table devices(
    id serial primary key,
    name varchar(255),
    owner_id int references owners(id)
);

insert into owners(name) values ('Owner 1');
insert into owners(name) values ('Owner 2');
insert into owners(name) values ('Owner 3');

insert into devices(name, owner_id) values ('Device 1', 1);
insert into devices(name, owner_id) values ('Device 2', 2);
insert into devices(name, owner_id) values ('Device 3', 3);
insert into devices(name, owner_id) values ('Device 4', null);
insert into devices(name, owner_id) values ('Device 5', null);
insert into devices(name, owner_id) values ('Device 6', 1);

select * from devices d left join owners o on d.owner_id = o.id;

select * from devices d left join owners o on d.owner_id = o.id where o.id is null;

select * from owners o left join devices d on o.id = d.owner_id;

select * from devices d left join owners o on d.owner_id = o.id;
select * from owners o right join devices d on d.owner_id = o.id;

select * from owners o left join devices d on o.id = d.owner_id;
select * from devices d right join owners o on d.owner_id = o.id;

select * from devices d full join owners o on d.owner_id = o.id;

select * from devices d cross join owners o;




create table departments(
    id serial primary key,
    name varchar(30)
);

create table employees(
    id serial primary key,
    name varchar(30),
    department_id int references departments(id)
);

insert into departments (name) values ('department 1');
insert into departments (name) values ('department 2');
insert into departments (name) values ('department 3');

insert into employees (name, department_id) values ('employee 1', 1);
insert into employees (name, department_id) values ('employee 2', 1);
insert into employees (name, department_id) values ('employee 3', 3);
insert into employees (name, department_id) values ('employee 4', 1);
insert into employees (name, department_id) values ('employee 5', null);


select * from departments;
select * from employees;

--left
select * from departments d left join employees e on e.department_id = d.id;

--right
select * from departments d right join employees e on e.department_id = d.id;

--full
select * from departments d full join employees e on e.department_id = d.id;

--cross
select * from departments d cross join employees e;

--null departments
select * from departments d left join employees e on e.department_id = d.id
where e.department_id is null ;

--left same
select * from departments d left join employees e on d.id = e.department_id
where e.department_id = 1;

--right same
select * from departments d right join employees e on d.id = e.department_id
where d.id = 1;

CREATE TABLE teens (
 	id serial primary key,
	name varchar(30),
	gender varchar(20)
);
INSERT INTO teens(name,gender) values ('Roman','male');
INSERT INTO teens(name,gender) values ('Romeo','male');
INSERT INTO teens(name,gender) values ('Khristina','female');
INSERT INTO teens(name,gender) values ('Jane','female');
INSERT INTO teens(name,gender) values ('Anastasia','female');

SELECT * FROM teens;

select t1.name as boy, t2.name as girl from teens t1 cross join teens t2
where t1.gender != t2.gender;



