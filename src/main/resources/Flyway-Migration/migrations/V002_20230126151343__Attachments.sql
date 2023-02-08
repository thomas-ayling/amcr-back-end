CREATE TABLE IF NOT EXISTS academy_project.attachments
(
    id                  uuid primary key not null,
    name                text             not null,
    size                bigint           not null,
    type                text             not null,
    crc                 bigint           not null,
    content             bytea,
    attachment_sequence SERIAL           not null
);

ALTER TABLE academy_project.attachments
    OWNER TO CURRENT_USER;