--liquibase formatted sql

--changeset Vadim:1
create type status as enum ('CANCELLED', 'APPROVED', 'DONE', 'REJECTED','CREATED');