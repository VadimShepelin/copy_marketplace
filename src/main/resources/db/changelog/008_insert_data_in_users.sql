--liquibase formatted sql

--changeset Vadim:1
INSERT INTO Users (id, firstname, lastname, email)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Ivan', 'Petrov', 'ivan.petrov@example.com'),
       ('550e8400-e29b-41d4-a716-446655440001', 'Anna', 'Ivanova', 'anna.ivanova@example.com'),
       ('550e8400-e29b-41d4-a716-446655440002', 'Sergey', 'Sidorov', 'sergey.sidorov@example.com'),
       ('550e8400-e29b-41d4-a716-446655440003', 'Elena', 'Smirnova', 'elena.smirnova@example.com'),
       ('550e8400-e29b-41d4-a716-446655440004', 'Dmitry', 'Kozlov', 'dmitry.kozlov@example.com');
