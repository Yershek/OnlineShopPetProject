create table if not exists baskets(
    id bigserial primary key
);

create table if not exists users(
    id bigserial primary key,
    username varchar not null,
    password varchar not null,
    active_code varchar,
    email varchar not null,
    basket_id bigint references baskets(id) unique
);

create table if not exists roles(
    id bigserial primary key,
    role_name varchar not null unique
);

create table if not exists m2m_users_roles(
    user_id bigint references users(id),
    role_id bigint references roles(id)
);

create table if not exists reviews(
    id bigserial primary key,
    description varchar not null
);

create table if not exists videos(
    id bigserial primary key,
    file_name varchar not null
);

create table if not exists images(
    id bigserial primary key,
    file_name varchar not null
);

create table if not exists products(
    id bigserial primary key,
    price bigint not null,
    description varchar not null,
    title varchar not null ,
    compound varchar not null ,
    discount bigint not null ,
    active varchar default 'ACTIVE'
);

create table if not exists m2m_product_images(
    product_id bigint references products(id) unique,
    images_id bigint references images(id)
);

create table if not exists m2m_product_video(
    product_id bigint references products(id) unique,
    video_id bigint references videos(id)
);

create table if not exists m2m_product_reviews(
    product_id bigint references products(id) unique,
    reviews_id bigint references reviews(id)
);

create table if not exists m2m_baskets_products(
    basket_id bigint references baskets(id) unique,
    product_id bigint references products(id)
);

insert into roles(role_name)
values ('ADMIN');

insert into roles(role_name)
values ('USER');
