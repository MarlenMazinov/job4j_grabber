create table if not exists posts (
id serial unique,
name text,
text text,
link text unique,
created timestamp
);