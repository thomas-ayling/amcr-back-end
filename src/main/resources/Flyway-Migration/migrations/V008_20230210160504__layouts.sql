create table if not exists layout
(
    id         uuid primary key not null,
    name       text             not null,
    components jsonb[]
)