create table if not exists product
(
    id        integer primary key autoincrement,
    name      text not null,
    desc      text,
    imagePath text not null,
    price     integer
);

create table if not exists discount_product
(
    id             integer primary key autoincrement,
    productId      integer not null,
    discount_price integer
);

create table if not exists user
(
    id         integer primary key autoincrement,
    chat_id    integer unique not null,
    username   text,
    first_name text           not null,
    last_name  text,
    number     text,
    language   text           not null
);

create table if not exists buy_order_list
(
    id integer primary key autoincrement ,
    user_id integer not null ,
    product_id integer not null ,
    product_count integer not null ,
    total_price real not null ,
    buy_time text default current_time
);