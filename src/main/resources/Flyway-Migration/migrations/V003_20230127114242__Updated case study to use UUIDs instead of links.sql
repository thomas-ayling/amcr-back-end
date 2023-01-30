create table if not exists case_studies
(
    id               uuid primary key not null,
    case_study_order serial           not null,
    spotlight        boolean          not null,
    title            text             not null,
    overview         text             not null,
    body             jsonb[]            not null,
    attachment_ids   uuid[],
    cover_image_id   uuid             not null
);

ALTER TABLE case_studies
    OWNER TO CURRENT_USER;