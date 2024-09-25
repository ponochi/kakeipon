CREATE TABLE role_data
(
    role_id   BIGINT NOT NULL,
    role_name VARCHAR(255),
    CONSTRAINT pk_role_data PRIMARY KEY (role_id)
);

CREATE TABLE usr
(
    id      BIGINT NOT NULL,
    user_name    VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    email        VARCHAR(255),
    birthday     TIMESTAMP,
    phone_number VARCHAR(255),
    role_id      BIGINT,
    entry_date   TIMESTAMP,
    update_date  TIMESTAMP,
    CONSTRAINT pk_usr PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE role
(
    role_id   BIGINT NOT NULL,
    role_name VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE user
(
    id      BIGINT NOT NULL,
    user_name    VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    email        VARCHAR(255),
    birthday     TIMESTAMP WITHOUT TIME ZONE,
    phone_number VARCHAR(255),
    role_id      BIGINT,
    entry_date   TIMESTAMP WITHOUT TIME ZONE,
    update_date  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);