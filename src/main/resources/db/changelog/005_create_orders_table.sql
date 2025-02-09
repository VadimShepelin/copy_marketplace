--liquibase formatted sql

--changeset Vadim:1

create table Orders(
    id uuid,
    total_cost numeric not null,
    status status,
    user_id uuid,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    PRIMARY KEY (id,user_id)
)