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
    file_size    text,
    data         bytea,
    download_uri text,
    feedback_id  uuid references feedback (id) on delete cascade
);

-- {"firstName":"A", "lastName": "B", "emailAddress": "C", "feedbackBody":"D", "bookName":"E", "bookLink": "F"}