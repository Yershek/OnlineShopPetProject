create table users(
    id bigserial primary key,
    username varchar not null,
    password varchar not null,
    active_code varchar,
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

create table if not exists products(
    id bigserial primary key,
    price bigint not null,
    description varchar not null,
    title varchar not null ,
    compound varchar not null ,
    discount int,
    image_id bigint references images(id),
    video_id bigint references videos(id),
    reviews_id bigint references reviews(id),
    active varchar default 'ACTIVE'
);

create table if not exists baskets(
    id bigserial primary key,
    product_id bigint references products(id),
    user_id bigint references users(id)
);

create table if not exists reviews(
    id bigserial primary key,
    description varchar not null,
    product_id bigint references products(id)
);

create table if not exists videos(
    id bigserial primary key,
    file_name varchar not null
);

create table if not exists images(
    id bigserial primary key,
    file_name varchar not null
);

insert into roles(role_name)
values ('ADMIN');

insert into roles(role_name)
values ('USER');
