-- liquibase formatted sql

-- changeset distrog:1

create table notification_task (
    id serial,
    chat_id bigint,
    message_text varchar(250),
    shedule_date timestamp
)