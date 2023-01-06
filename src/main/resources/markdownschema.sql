create table if not exists markdown
(
    id           uuid primary key,
    order_number serial,
    name         varchar(50) unique not null,
    content      text
);