--liquibase formatted sql

--changeset Vadim:1
create table order_items
(
    id uuid PRIMARY KEY,
    order_id uuid,
    quantity bigint not null,
    sku character varying(32) not null
);

