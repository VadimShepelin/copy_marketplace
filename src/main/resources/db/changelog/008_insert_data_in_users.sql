--liquibase formatted sql

--changeset Vadim:1
INSERT INTO Users (id, firstname, lastname)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'Ivan', 'Petrov'),
    ('550e8400-e29b-41d4-a716-446655440001', 'Anna', 'Ivanova'),
    ('550e8400-e29b-41d4-a716-446655440002', 'Sergey', 'Sidorov'),
    ('550e8400-e29b-41d4-a716-446655440003', 'Elena', 'Smirnova'),
    ('550e8400-e29b-41d4-a716-446655440004', 'Dmitry', 'Kozlov');