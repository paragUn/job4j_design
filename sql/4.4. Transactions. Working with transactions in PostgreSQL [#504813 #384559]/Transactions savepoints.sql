begin transaction;

update products set count = 1000 where producer = 'producer_80';

savepoint first;

update products set price = 1000 where producer = 'producer_80';

savepoint second;

drop table products;

select * from products;

rollback to second;
select * from products;
rollback to first;
select * from products;
rollback;