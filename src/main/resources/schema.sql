drop table if exists PUBLIC.USR cascade;

create table if not exists PUBLIC.USR
(
    USER_ID      BIGINT       not null
        constraint PK_USR
            primary key,
    NICK_NAME    VARCHAR(255) not null,
    LAST_NAME    VARCHAR(255),
    FIRST_NAME   VARCHAR(255),
    PASSWORD     VARCHAR(255) not null,
    EMAIL        VARCHAR(255) not null,
    BIRTHDAY     TIMESTAMP(6) not null,
    PHONE_NUMBER VARCHAR(255) not null,
    ROLE_NAME    VARCHAR(255) not null,
    ENTRY_DATE   TIMESTAMP    not null,
    UPDATE_DATE  TIMESTAMP
);

