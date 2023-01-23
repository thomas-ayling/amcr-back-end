create table if not exists academy_project.feedback
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

ALTER TABLE academy_project.feedback
    OWNER TO CURRENT_USER;

create table if not exists academy_project.files
(
    id           uuid primary key,
    file_name    text   not null,
    file_type    text   not null,
    file_size    bigint not null,
    data         bytea  not null,
    download_uri text   not null,
    feedback_id  uuid references feedback (id) on delete cascade
);

ALTER TABLE academy_project.files
    OWNER TO CURRENT_USER;

create table if not exists academy_project.case_studies
(
    id               uuid primary key not null,
    spotlight        boolean          not null,
    title            text             not null,
    overview         text             not null,
    cover_image_link text             not null,
    body             jsonb            not null,
    download_links   text[]
);

ALTER TABLE academy_project.case_studies
    OWNER TO CURRENT_USER;

create table if not exists academy_project.diagram
(
    id    uuid primary key not null,
    nodes jsonb[]          not null
);

ALTER TABLE academy_project.diagram
    OWNER TO CURRENT_USER;

create table if not exists academy_project.attachments
(
    id           uuid primary key not null,
    name         text             not null,
    size         bigint           not null,
    type         text             not null,
    crc          bigint,
    metadata     jsonb,
    content      bytea,
    download_uri text             not null
);

ALTER TABLE academy_project.attachments
    OWNER TO CURRENT_USER;

-- Object format:

-- {
--     "spotlight": true,
--     "title": "This is the title - 8 parts ",
--     "overview": "This is the overview",
--     "coverImageLink": "This is a link :/",
--     "body": {
--         "content": [
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "A paragraph with *emphasis* and **strong importance**. > A block quote with ~strikethrough~ and a URL: https://reactjs.org. \n * Lists \n * [ ] todo \n * [x] done \n A table:\n \n | a | b |\n | - | - |"
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "## This is the header of the block \n This is even more text, just doing it for fun as you do ~~hello~~ \n #### subheading3 \n - bullet "
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "### This is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template,\n - this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, \n * [x] this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template"
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "This is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template"
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "## This is the header of the block \n This is even more text, just doing it for fun as you do ~~hello~~ \n #### subheading3 \n - bullet "
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "### This is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template,\n - this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, \n * [x] this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template"
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "## This is the header of the block \n This is even more text, just doing it for fun as you do ~~hello~~ \n #### subheading3 \n - bullet "
--             },
--             {
--                 "imageLink": "https://picsum.photos/500/300",
--                 "markdownText": "### This is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template,\n - this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, \n * [x] this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template, this is a lot of markdown text to be used as a template"
--             }
--         ]
--     },
--         "downloadLinks": ["This is a PDF link", "This is a PPTX link"]
--     }
-- }