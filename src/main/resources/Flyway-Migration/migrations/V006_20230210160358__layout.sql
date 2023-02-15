create table if not exists academy_project.layout(
    id           uuid primary key not null,
    name         text not null,
    components   jsonb[]
)