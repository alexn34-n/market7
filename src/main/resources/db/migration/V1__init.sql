create table products(
    id bigserial primary key not null ,
    name varchar not null,
    price int not null,
    created_date_time timestamp not null default now(),
    updated_date_time timestamp not null default now()

);

create table order_items
(
    id bigserial  primary key not null,
    product_id  bigint not null,
    product_price int not null,
    quantity int not null

);

create table users
(
    id bigserial  primary key,
    name varchar not null,
    password  varchar not null,
    email varchar not null

);

create table  roles
(
    id bigserial  primary key not null,
    name varchar not null

);

create  table users_roles
(
    user_id bigserial  not null,
    role_id bigserial  not null,
    primary key (user_id,role_id),
    foreign key (user_id) references  users(id),
    foreign key (role_id) references roles(id)

);



insert into users (name, password, email)
values
('user', '$2a$10$AL3BxKTjXlbu7.9NA5hRPutAw752Mo3pA8QnZV7FnGbxOxyHAuqcG', 'user@gmail.com');


insert into products(name,price)
values ('Tea',10),
       ('Sugar',5),
       ('Coffee',20),
       ('Coffee1',25),
       ('Coffee2',28),
       ('Coffee3',12),
       ('Coffee4',15),
       ('Coffee5',16),
       ('Coffee6',22),
       ('Coffee7',30),
       ('Coffee8',21),
       ('Coffee9',10),
       ('Coffee10',40),
       ('Coffee11',18),
       ('Coffee12',24),
       ('Coffee13',8),
       ('Coffee14',34),
       ('Coffee15',19);