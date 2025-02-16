--liquibase formatted sql

--changeset Vadim:1
create table Users(
     id uuid PRIMARY KEY,
     firstname varchar(64) not null,
     lastname varchar(64) not null,
     email varchar(32) not null
)
