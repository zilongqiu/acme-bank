CREATE TABLE IF NOT EXISTS account (
    id bigint not null,
    account_number integer not null,
    balance bigint not null,
    birthdate date,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    phone_number varchar(255),
    primary key (id)
);