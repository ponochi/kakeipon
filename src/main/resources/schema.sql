DROP TABLE IF EXISTS kp.tbl_specification CASCADE;
DROP TABLE IF EXISTS kp.tbl_unit CASCADE;
DROP TABLE IF EXISTS kp.tbl_currency CASCADE;
DROP TABLE IF EXISTS kp.tbl_tax_type CASCADE;
DROP TABLE IF EXISTS kp.tbl_specification_group CASCADE;
DROP TABLE IF EXISTS kp.tbl_balance_type CASCADE;
DROP TABLE IF EXISTS kp.tbl_account_info CASCADE;
DROP TABLE IF EXISTS kp.tbl_account_and_balance CASCADE;
DROP TABLE IF EXISTS kp.tbl_shop CASCADE;
DROP TABLE IF EXISTS kp.tbl_third_class_by_order CASCADE;
DROP TABLE IF EXISTS kp.tbl_third_class CASCADE;
DROP TABLE IF EXISTS kp.tbl_second_class_by_order CASCADE;
DROP TABLE IF EXISTS kp.tbl_second_class CASCADE;
DROP TABLE IF EXISTS kp.tbl_first_class CASCADE;
DROP TABLE IF EXISTS kp.tbl_role CASCADE;
DROP TABLE IF EXISTS kp.tbl_user CASCADE;
DROP TABLE IF EXISTS SPRING_SESSION_ATTRIBUTES;
DROP TABLE IF EXISTS SPRING_SESSION;
DROP SEQUENCE IF EXISTS kp.tbl_specification_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_unit_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_currency_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_tax_type_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_specification_group_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_balance_type_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_account_info_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_account_and_balance_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_shop_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_third_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_second_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_first_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_user_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_role_seq CASCADE;

CREATE TABLE SPRING_SESSION
(
    PRIMARY_ID            CHAR(36) NOT NULL,
    SESSION_ID            CHAR(36) NOT NULL,
    CREATION_TIME         BIGINT   NOT NULL,
    LAST_ACCESS_TIME      BIGINT   NOT NULL,
    MAX_INACTIVE_INTERVAL INT      NOT NULL,
    EXPIRY_TIME           BIGINT   NOT NULL,
    PRINCIPAL_NAME        VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID CHAR(36)     NOT NULL,
    ATTRIBUTE_NAME     VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES    BYTEA        NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_role_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_role -- 権限テーブル
(
    role_id   BIGINT DEFAULT
                         nextval('kp.tbl_role_seq'), -- 権限ID
    role_name VARCHAR(128) NOT NULL,                 -- 権限名
    PRIMARY KEY (role_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_user_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_user -- ユーザテーブル
(
    user_id      BIGINT DEFAULT
                            nextval('kp.tbl_user_seq'), -- ユーザID
    nick_name    VARCHAR(255) NOT NULL,                 -- ニックネーム
    last_name    VARCHAR(255),                          -- 苗字 (任意)
    first_name   VARCHAR(255),                          -- 名前 (任意)
    password     VARCHAR(255) NOT NULL,                 -- パスワード
    email        VARCHAR(255) NOT NULL,                 -- メールアドレス
    birth_day TIMESTAMPTZ NOT NULL,                     -- 誕生日
    phone_number VARCHAR(255) NOT NULL,                 -- 電話番号
    role_id      BIGINT       NOT NULL,                 -- 権限ID
    entry_date   TIMESTAMPTZ  NOT NULL,                 -- 登録日時
    update_date  TIMESTAMPTZ,                           -- 更新日時
    PRIMARY KEY (user_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_first_class_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_first_class -- 支出入分類テーブル
(
    first_class_id   BIGINT
        DEFAULT nextval('kp.tbl_first_class_seq'), -- 支出入分類ID
    first_class_name VARCHAR(128) NOT NULL,        -- 支出入分類名
    PRIMARY KEY (first_class_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_second_class_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_second_class -- 費目大分類テーブル
(
    second_class_id   BIGINT
        DEFAULT nextval('kp.tbl_second_class_seq'), -- 費目大分類ID
    first_class_id    BIGINT,                       -- 支出入分類ID (任意)
    second_class_name VARCHAR(128) NOT NULL,        -- 費目大分類名
    PRIMARY KEY (second_class_id),
    FOREIGN KEY (first_class_id)
        REFERENCES kp.tbl_first_class(first_class_id)
);
CREATE TABLE IF NOT EXISTS kp.tbl_second_class_by_order -- 費目大分類ソートテーブル
(
    second_class_id BIGINT,                               -- 費目大分類ID (任意)
    first_class_id  BIGINT                NOT NULL
        REFERENCES kp.tbl_second_class (second_class_id), -- 支出入分類ID
    order_number    BIGINT,                               -- ソート順 (任意)
    disabled        BOOLEAN DEFAULT FALSE NOT NULL,       -- 無効フラグ (true: 無効, false: 有効)
    PRIMARY KEY (second_class_id, first_class_id, order_number)

);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_third_class_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_third_class -- 費目小分類テーブル
(
    third_class_id   BIGINT  DEFAULT
                                 nextval('kp.tbl_third_class_seq'), -- 費目小分類ID
    second_class_id  BIGINT,                                        -- 費目大分類ID (任意)
    first_class_id   BIGINT,                                        -- 支出入分類ID (任意)
    third_class_name VARCHAR(128)          NOT NULL,                -- 費目小分類名
    disabled         BOOLEAN DEFAULT FALSE NOT NULL,                -- 無効フラグ (true: 無効, false: 有効)
    PRIMARY KEY (third_class_id, second_class_id, first_class_id)
);
CREATE TABLE IF NOT EXISTS kp.tbl_third_class_by_order -- 費目小分類ソートテーブル
(
    third_class_id  BIGINT, -- 費目小分類ID (任意)
    second_class_id BIGINT, -- 費目大分類ID (任意)
    first_class_id  BIGINT, -- 支出入分類ID (任意)
    order_number    BIGINT, -- ソート順 (任意)
    PRIMARY KEY (third_class_id, second_class_id, first_class_id, order_number),
    FOREIGN KEY (third_class_id, second_class_id, first_class_id)
        REFERENCES kp.tbl_third_class(third_class_id, second_class_id, first_class_id)
);


CREATE SEQUENCE IF NOT EXISTS kp.tbl_shop_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_shop -- 店舗情報テーブル
(
    shop_id         BIGINT DEFAULT
                               nextval('kp.tbl_shop_seq'), -- 店舗ID
    shop_name       VARCHAR(255) NOT NULL,                 -- 店舗名
    branch_name     VARCHAR(255),                          -- 支店名 (任意)
    shop_url        VARCHAR(255),                          -- URL (任意)
    phone_number    VARCHAR(255),                          -- 電話番号 (任意)
    email           VARCHAR(255),                          -- メールアドレス (任意)
    open_shop_date  DATE,                                  -- 開店日 (任意)
    close_shop_date DATE,                                  -- 閉店日 (任意)
    shop_memo       TEXT,                                  -- 1000文字まで   -- メモ (任意)
    entry_date      TIMESTAMPTZ  NOT NULL,                 -- 登録日時
    update_date     TIMESTAMPTZ,                           -- 更新日時
    PRIMARY KEY (shop_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_account_and_balance_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_account_and_balance -- 口座収支複合キーテーブル
(
    account_and_balance_id BIGINT DEFAULT
                                      nextval('kp.tbl_account_and_balance_seq'), -- 口座収支複合キーID
    account_source_id      BIGINT,                                               -- 口座支出元ID
    account_destination_id BIGINT,                                               -- 収支収入先ID
    entry_date             TIMESTAMPTZ NOT NULL,                                 -- 登録日時
    update_date            TIMESTAMPTZ,                                          -- 更新日時
    PRIMARY KEY (account_and_balance_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_account_info_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_account_info -- 口座情報テーブル
(
    account_id   BIGINT DEFAULT
                            nextval('kp.tbl_account_info_seq'), -- 口座ID
    account_name VARCHAR(255) NOT NULL,                         -- 口座名
    branch_name  VARCHAR(255),                                  -- 支店名 (任意)
    entry_date   TIMESTAMPTZ  NOT NULL,                         -- 登録日時
    update_date  TIMESTAMPTZ,                                   -- 更新日時
    PRIMARY KEY (account_id)
);
CREATE SEQUENCE IF NOT EXISTS kp.tbl_balance_type_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_balance_type -- 収支テーブル
(
    balance_type_id     BIGINT DEFAULT
                            nextval('kp.tbl_balance_type_seq'),  -- 収支ID
    balance_type_name   VARCHAR(2) NOT NULL,                -- 収支名
    PRIMARY KEY (balance_type_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_specification_group_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_specification_group -- 明細グループテーブル
(
    specification_group_id     BIGINT DEFAULT
                                          nextval('kp.tbl_specification_group_seq'), -- 明細グループID
    user_id                    BIGINT      NOT NULL,                                 -- ユーザID
    shop_id                    BIGINT      NOT NULL,                                 -- 店舗ID
    receiving_and_payment_date DATE        NOT NULL,                                 -- 受取支払日
    receiving_and_payment_time TIME        NOT NULL,                                 -- 受取支払時間
    balance_type_id            BIGINT      NOT NULL,                                 -- 受取支払種別ID (支出 / 収入 / 振替)
    account_and_balance_id     BIGINT,                                               -- 口座ID (任意) (支出 / 振替: 送金元)
    memo                       TEXT,                                                 -- メモ (任意) 1000文字まで
    entry_date                 TIMESTAMPTZ NOT NULL,                                 -- 登録日時
    update_date                TIMESTAMPTZ,                                          -- 更新日時
    PRIMARY KEY (specification_group_id, user_id),
    FOREIGN KEY (user_id) REFERENCES kp.tbl_user (user_id),
    FOREIGN KEY (shop_id) REFERENCES kp.tbl_shop (shop_id),
    FOREIGN KEY (account_and_balance_id)
        REFERENCES kp.tbl_account_and_balance (account_and_balance_id),
    FOREIGN KEY (balance_type_id) REFERENCES kp.tbl_balance_type (balance_type_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_tax_type_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_tax_type -- 消費税種別テーブル
(
    tax_type_id     BIGINT DEFAULT
                          nextval('kp.tbl_tax_type_seq'),   -- 消費税種別ID
    tax_type_name   VARCHAR(128) NOT NULL,                  -- 消費税種別
    tax_rate        DECIMAL(3, 2) NOT NULL,                 -- 消費税率
    PRIMARY KEY (tax_type_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_currency_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_currency -- 通貨テーブル
(
    currency_id     BIGINT DEFAULT
                           nextval('kp.tbl_currency_seq'),  -- 通貨ID
    currency_name   VARCHAR(3) NOT NULL,                    -- 通貨名
    PRIMARY KEY (currency_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_unit_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_unit -- 単位テーブル
(
    unit_id   BIGINT DEFAULT
                       nextval('kp.tbl_unit_seq'),  -- 単位ID
    unit_name VARCHAR(16) NOT NULL,                 -- 単位名
    PRIMARY KEY (unit_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_specification_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_specification -- 明細テーブル
(
    specification_group_id BIGINT         NOT NULL,                 -- 明細グループID
    specification_id       BIGINT
        DEFAULT nextval('kp.tbl_specification_seq'),                -- 明細ID
    user_id                BIGINT         NOT NULL,                 -- ユーザID
    name                   VARCHAR(255)   ,                         -- 商品名
    price                  DECIMAL(10, 2) NOT NULL,                 -- 価格
    currency_id            BIGINT,                                  -- 通貨名 (任意 : USD, EUR, ...)
    unit_id                BIGINT         NOT NULL DEFAULT 1,       -- 単位
    quantity               BIGINT         NOT NULL,                 -- 数量
    tax_type_id            BIGINT         NOT NULL DEFAULT 1,       -- 消費税種別ID
    tax                    DECIMAL(10, 2),                          -- 消費税額 (任意)
    memo                   TEXT,                                    -- 1000文字まで                -- メモ (任意)
    entry_date             TIMESTAMPTZ    NOT NULL,                 -- 登録日時
    update_date            TIMESTAMPTZ,                             -- 更新日時
    PRIMARY KEY (specification_group_id, specification_id, user_id),
    FOREIGN KEY (specification_group_id, user_id)
        REFERENCES kp.tbl_specification_group (specification_group_id, user_id),
    FOREIGN KEY (tax_type_id) REFERENCES kp.tbl_tax_type (tax_type_id),
    FOREIGN KEY (currency_id) REFERENCES kp.tbl_currency (currency_id),
    FOREIGN KEY (unit_id) REFERENCES kp.tbl_unit (unit_id)
);
