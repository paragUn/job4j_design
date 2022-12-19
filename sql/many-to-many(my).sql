create table actors(
	id serial primary key,
	name varchar(255)
);

create table films(
	id serial primary key,
	title varchar(255)
);

create table actors_films(
	id serial primary key,
	actor_id int references actors(id),
	film_id int references films(id)
);

insert into actors(name) values('Charles Spencer «Charlie» Chaplin');
insert into actors(name) values('Marlon Brando Jr');
insert into actors(name) values('Alfredo James (Al) Pacino');

insert into films(title) values('The Godfather');
insert into films(title) values('A Countess From Hong Kong');
insert into films(title) values('Scarface');

insert into actors_films(actor_id, film_id) values(1, 2);
insert into actors_films(actor_id, film_id) values(2, 1);
insert into actors_films(actor_id, film_id) values(2, 2);
insert into actors_films(actor_id, film_id) values(3, 1);
insert into actors_films(actor_id, film_id) values(3, 3);