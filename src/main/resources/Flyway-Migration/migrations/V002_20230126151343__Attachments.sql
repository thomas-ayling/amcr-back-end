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