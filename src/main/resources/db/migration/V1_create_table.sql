create table users(
    id bigserial primary key,
    username varchar not null,
    password varchar not null,
    email varchar not null
);

create table roles(
    id bigserial primary key,
    role_name varchar not null unique
);

create table if not exists m2m_users_roles(
    ser_id bigint references users(id),
    role_id bigint references roles(id)
);

create table if not exists product(
    id bigserial primary key,
    price bigint not null,
    description varchar not null,
    title varchar,
    grade float,
    compound varchar
);

create table if not exists reviews(
    id bigserial primary key,
    description varchar not null
);

insert into roles(role_name)
values ('ADMIN');

insert into roles(role_name)
values ('USER');
