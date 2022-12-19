create table someEntity (
	id serial primary key,
    name varchar(255),
	somecharacteristic text,
	price integer
);

insert into someEntity (name, somecharacteristic, price)
values 
	('someEntity1', 'this is some entity # 1', 10),
	('someEntity2', 'this is some entity # 2', 20),
	('someEntity3', 'this is some entity # 3', 30);
select * from someEntity;

update someEntity set name = 'someEntity1111'
where name = 'someEntity1';
select * from someEntity;

delete from someEntity
where name = 'someEntity1111';
select * from someEntity;