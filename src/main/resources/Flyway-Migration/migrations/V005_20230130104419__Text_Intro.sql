create table if not exists text_intro
(
    id              uuid primary key not null,
    title           text             not null,
    description     text             not null,
    location        text             not null
);

ALTER TABLE text_intro
    OWNER TO CURRENT_USER;