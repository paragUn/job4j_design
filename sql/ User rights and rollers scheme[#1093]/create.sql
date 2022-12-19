create table roles(
    id serial primary key,
    role varchar(20)
);

create table users(
    id serial primary key, 
    username varchar(30),
    role_id int references roles(id)
);

create table rules(
    id serial primary key,
    rule varchar(50)
);

create table roles_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table item(
    id serial primary key,
    item varchar(20),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);

create table comments(
    id serial primary key,
    comment text,
    item_id int references item(id)
);

create table attachs(
    id serial primary key, 
    attach text,
    item_id int references item(id)
);

create table category(
    id serial primary key,
    category varchar(20)
);

create table state(
    id serial primary key,
    state varchar(20)
);