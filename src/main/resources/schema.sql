DROP TABLE IF EXISTS kp.tbl_account CASCADE;
DROP TABLE IF EXISTS kp.tbl_shop CASCADE;
DROP TABLE IF EXISTS kp.tbl_specification CASCADE;
DROP TABLE IF EXISTS kp.tbl_specification_group CASCADE;
DROP TABLE IF EXISTS kp.tbl_third_class_by_order CASCADE;
DROP TABLE IF EXISTS kp.tbl_third_class CASCADE;
DROP TABLE IF EXISTS kp.tbl_second_class_by_order CASCADE;
DROP TABLE IF EXISTS kp.tbl_second_class CASCADE;
DROP TABLE IF EXISTS kp.tbl_first_class CASCADE;
DROP TABLE IF EXISTS kp.tbl_role CASCADE;
DROP TABLE IF EXISTS kp.tbl_user CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_account_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_shop_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_specification_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_specification_group_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_third_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_second_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_first_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_user_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_role_seq CASCADE;

--select setval('kp.tbl_role_seq', 1, FALSE);
--select setval('kp.tbl_user_seq', 1, FALSE);

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

CREATE SEQUENCE IF NOT EXISTS kp.tbl_first_class_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_first_class
(
    first_class_id    BIGINT       DEFAULT nextval('kp.tbl_first_class_seq'),
    first_class_name  VARCHAR(128) NOT NULL,
--    disabled          BOOLEAN      DEFAULT FALSE NOT NULL,
    PRIMARY KEY (first_class_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_second_class_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_second_class
(
    second_class_id   BIGINT       DEFAULT nextval('kp.tbl_second_class_seq'),
    first_class_id    BIGINT       ,
    second_class_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (second_class_id),
    FOREIGN KEY (first_class_id)
        REFERENCES kp.tbl_first_class(first_class_id)
);
CREATE TABLE IF NOT EXISTS kp.tbl_second_class_by_order
(
    second_class_id   BIGINT       ,
    first_class_id    BIGINT       NOT NULL REFERENCES kp.tbl_second_class(second_class_id),
    order_number      BIGINT       ,
    disabled          BOOLEAN      DEFAULT FALSE NOT NULL,
    PRIMARY KEY (second_class_id, first_class_id, order_number)

);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_third_class_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_third_class
(
    third_class_id    BIGINT       DEFAULT nextval('kp.tbl_third_class_seq'),
    second_class_id   BIGINT       ,
    first_class_id    BIGINT       ,
    third_class_name  VARCHAR(128) NOT NULL,
    disabled          BOOLEAN      DEFAULT FALSE NOT NULL,
    PRIMARY KEY (third_class_id, second_class_id, first_class_id)
);
CREATE TABLE IF NOT EXISTS kp.tbl_third_class_by_order
(
    third_class_id    BIGINT       ,
    second_class_id   BIGINT       ,
    first_class_id    BIGINT       ,
    order_number      BIGINT       ,
    disabled          BOOLEAN      DEFAULT FALSE NOT NULL,
    PRIMARY KEY (third_class_id, second_class_id, first_class_id, order_number),
    FOREIGN KEY (third_class_id, second_class_id, first_class_id)
        REFERENCES kp.tbl_third_class(third_class_id, second_class_id, first_class_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_specification_group_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_specification_group
(
    specification_group_id      BIGINT      DEFAULT nextval('kp.tbl_specification_group_seq'),
    user_id                     BIGINT      NOT NULL,
    shop_id                     BIGINT      NOT NULL,
    receiving_and_payment_date  DATE        NOT NULL,
    receiving_and_payment_time  TIME        NOT NULL,
    receiving_and_payment_type  BIGINT      NOT NULL,
    account_source              BIGINT      NOT NULL,
    account_destination         BIGINT,
    memo                        TEXT,       -- 1000文字まで
    entry_date                  TIMESTAMPTZ NOT NULL,
    update_date                 TIMESTAMPTZ,
    PRIMARY KEY (specification_group_id, user_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_specification_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_specification
(
    specification_group_id  BIGINT          NOT NULL,
    specification_id        BIGINT          DEFAULT nextval('kp.tbl_specification_seq'),
    user_id                 BIGINT          NOT NULL,
    item_name               VARCHAR(255)    NOT NULL,
    items_jpy_price         DECIMAL(10, 2)  NOT NULL,
    currency_name           VARCHAR(3)      NOT NULL,
    items_price             DECIMAL(10, 2)  NOT NULL,
    item_count              INTEGER         NOT NULL DEFAULT 1,
    memo                    TEXT,       -- 1000文字まで
    entry_date              TIMESTAMPTZ     NOT NULL,
    update_date             TIMESTAMPTZ,
    PRIMARY KEY (specification_group_id, specification_id, user_id),
    FOREIGN KEY (specification_group_id, user_id)
        REFERENCES kp.tbl_specification_group(specification_group_id, user_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_shop_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_shop
(
    shop_id         BIGINT          DEFAULT nextval('kp.tbl_shop_seq'),
    shop_name       VARCHAR(255)    NOT NULL,
    branch_name     VARCHAR(255)    ,
    shop_url        VARCHAR(255)    ,
    phone_number    VARCHAR(255)    ,
    email           VARCHAR(255)    ,
    open_shop_date  DATE            ,
    close_shop_date DATE            ,
    shop_memo       TEXT,       -- 1000文字まで
    entry_date      TIMESTAMPTZ     NOT NULL,
    update_date     TIMESTAMPTZ,
    PRIMARY KEY (shop_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_account_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_account
(
    account_id      BIGINT          DEFAULT nextval('kp.tbl_account_seq'),
    account_name    VARCHAR(255)    NOT NULL,
    branch_name     VARCHAR(255)    ,
    entry_date      TIMESTAMPTZ     NOT NULL,
    update_date     TIMESTAMPTZ,
    PRIMARY KEY (account_id)
);
