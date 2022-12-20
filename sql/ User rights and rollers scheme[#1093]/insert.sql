insert into roles(role) values ('Admin');
insert into roles(role) values ('Noob');
insert into roles(role) values ('Mid user');
select * from roles;

insert into users(username, role_id) values ('Roman', 1);
insert into users(username, role_id) values ('Jane', 3);
insert into users(username, role_id) values ('Kass', 2);
select * from users;

insert into rules(rule) values ('Do everything you want');
insert into rules(rule) values ('Do not touch anything');
insert into rules(rule) values ('Can view');
insert into rules(rule) values ('Can use');
select * from rules;

insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (1, 3);
insert into roles_rules(role_id, rule_id) values (1, 4);
insert into roles_rules(role_id, rule_id) values (2, 2);
insert into roles_rules(role_id, rule_id) values (3, 3);
insert into roles_rules(role_id, rule_id) values (3, 4);

insert into category(category) values ('Sometime');
insert into category(category) values ('Always');
insert into category(category) values ('Never');

insert into state(state) values ('Where pc free');
insert into state(state) values ('Where pc not free');

insert into item(item, user_id, category_id, state_id) values ('Stay out', 3, 2, 2);
insert into item(item, user_id, category_id, state_id) values ('Play game', 2, 1, 1);
insert into item(item, user_id, category_id, state_id) values ('Learn Java', 1, 2, 1);

insert into comments(comment, item_id) values ('Funny game', 2);
insert into comments(comment, item_id) values ('Do not touch my pc', 1);
insert into comments(comment, item_id) values ('Like a senior', 3);

insert into attachs(attach, item_id) values ('Idea', 1);
insert into attachs(attach, item_id) values ('Dota 2', 2);
insert into attachs(attach, item_id) values ('Power switch', 3);