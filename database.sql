create database book;
use book;
create table books(
    id int auto_increment primary key ,
    name nvarchar(40) not null ,
    description nvarchar(40),
    price double,
    producer nvarchar(40),
    id_category int not null ,
    foreign key (id_category) references category (id)
);

drop table books;
create table category(
    id int primary key auto_increment,
    name nvarchar(40) not null
);

select * from category;
insert into category (name) values ('kinh di');
select id, name from category where id = 1;
UPDATE book.category SET name = 'tinh yeu' WHERE id = 6;
DELETE FROM book.category WHERE id = 6;

select * from books;
insert into books (name, description, price, producer, id_category) values ();
select * from books where id = 2;
delete from books where id = 6;
