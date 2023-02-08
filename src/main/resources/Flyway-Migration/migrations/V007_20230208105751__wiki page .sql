create table wiki (
  id uuid primary key not null,
  title text not null,
  overview text not null,
  sub_title text not null,
  sub_overview text not null,
  sub_image uuid[],
  diagram uuid,
  foreign key (diagram) references diagram (id)
  match simple on update no action on delete no action
);