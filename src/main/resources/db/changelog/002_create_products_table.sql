--liquibase formatted sql

--changeset Vadim:1
create table products
(
    id              uuid PRIMARY KEY,
    name            varchar(64)                  not null,
    description     text                  not null,
    price           numeric not null check (price > 99),
    quantity        bigint,
    sku             character varying(32) not null unique,
    created_at      timestamp,
    quantity_update timestamp,
    category        categories
);

