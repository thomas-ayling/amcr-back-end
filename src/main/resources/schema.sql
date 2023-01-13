create table if not exists feedback
(
    id             uuid primary key,
    feedback_order serial,
    feedback_type  text,
    first_name     text,
    last_name      text,
    email_address  text,
    feedback_body  text,
    book_name      text,
    book_link      text
);

create table if not exists files
(
    id           uuid primary key,
    file_name    text,
    file_type    text,
    file_size    bigint,
    data         bytea,
    download_uri text,
    feedback_id  uuid references feedback (id) on delete cascade
);

create table if not exists library
(
    id          uuid primary key,
    title       text,
    genre       text,
    author      text, 
    available   boolean,
    reader      text,
    cover       text,
    email       text

);
