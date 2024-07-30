DROP TABLE IF EXISTS kp.tbl_role CASCADE;
DROP TABLE IF EXISTS kp.tbl_user CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_role_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_user_seq CASCADE;

--SELECT SETVAL('kp.tbl_role_seq', 1, FALSE);
--SELECT SETVAL('kp.tbl_user_seq', 1, FALSE);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_role_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_role
(
    role_id   BIGINT       DEFAULT nextval('kp.tbl_role_seq')
                           NOT NULL PRIMARY KEY,
    role_name VARCHAR(128) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_user_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_user
(
    user_id      BIGINT       DEFAULT nextval('kp.tbl_user_seq')
                              NOT NULL PRIMARY KEY,
    nick_name    VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255),
    first_name   VARCHAR(255),
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    birthday     TIMESTAMPTZ  NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    role_id      BIGINT       NOT NULL,
    entry_date   TIMESTAMPTZ,
    update_date  TIMESTAMPTZ
);
