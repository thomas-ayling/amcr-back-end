create table if not exists feedback
(
    id             uuid primary key,
    feedback_order serial,
    feedback_type  text not null,
    first_name     text,
    last_name      text,
    email_address  text,
    feedback_body  text,
    book_name      text,
    book_link      text
);

ALTER TABLE feedback
    OWNER TO CURRENT_USER;

create table if not exists files
(
    id           uuid primary key,
    file_name    text   not null,
    file_type    text   not null,
    file_size    bigint not null,
    data         bytea  not null,
    download_uri text   not null,
    feedback_id  uuid references feedback (id) on delete cascade
);

ALTER TABLE files
    OWNER TO CURRENT_USER;

create table if not exists case_studies
(
    id               uuid primary key not null,
    case_study_order serial           not null,
    spotlight        boolean          not null,
    title            text             not null,
    overview         text             not null,
    body             jsonb[]          not null,
    attachment_ids   uuid[],
    cover_image_id   uuid             not null
);

ALTER TABLE case_studies
    OWNER TO CURRENT_USER;

create table if not exists diagram
(
    id    uuid primary key not null,
    nodes jsonb[]          not null
);

ALTER TABLE diagram
    OWNER TO CURRENT_USER;

create table if not exists attachments
(
    id                  uuid primary key not null,
    name                text             not null,
    size                bigint           not null,
    type                text             not null,
    crc                 bigint           not null,
    content             bytea,
    attachment_sequence SERIAL           not null
);

ALTER TABLE attachments
    OWNER TO CURRENT_USER;

create table if not exists contacts
(
    id          uuid primary key not null,
    spotlight   boolean          not null,
    image_id    uuid             not null,
    full_name   text             not null,
    title       text             not null,
    description text             not null
);

ALTER TABLE contacts
    OWNER TO CURRENT_USER;