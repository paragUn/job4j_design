create table reader(
	id serial primary key,
	name_of_reader varchar(255)
);

create table books(
	id serial primary key,
	author varchar(255),
	title varchar(255),
	reader_id int references reader(id) 
);

insert into reader (name_of_reader) values('John Smith');
insert into reader (name_of_reader) values('Theodore John Kaczynski');
insert into books (author, title, reader_id) values ('G.R.R. Martin', 'Game of Thrones', 1);
insert into books (author, title, reader_id) values ('Game of Thrones', 'The Anarchist Cookbook', 2);

update books set author = 'William Powell'
where author = 'Game of Thrones';

select name_of_reader from reader where id in(select reader_id from books);