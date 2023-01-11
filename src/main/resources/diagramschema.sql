create table if not exists diagram
(
    id      uuid primary key,
    node_id int unique not null,
    title   text,
    body    text
)