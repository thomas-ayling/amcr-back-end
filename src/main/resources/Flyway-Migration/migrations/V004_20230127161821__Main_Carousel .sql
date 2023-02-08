create table if not exists academy_project.main_carousel
(
    id               uuid primary key   not null,
    titles           text[]             not null,
    descriptions     text[],
    location         text               not null,
    image_ids         uuid[]             not null
)

ALTER TABLE academy_project.main_carousel
    OWNER TO CURRENT_USER;