DROP TABLE IF EXISTS kp.tbl_specification CASCADE;
DROP TABLE IF EXISTS kp.tbl_specification_group CASCADE;
DROP TABLE IF EXISTS kp.tbl_balance CASCADE;
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
DROP SEQUENCE IF EXISTS kp.tbl_specification_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_specification_group_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_balance_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_account_info_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_account_and_balance_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_shop_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_third_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_second_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_first_class_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_user_seq CASCADE;
DROP SEQUENCE IF EXISTS kp.tbl_role_seq CASCADE;

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
    birthday     TIMESTAMPTZ  NOT NULL,                 -- 誕生日
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
CREATE SEQUENCE IF NOT EXISTS kp.tbl_balance_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_balance -- 収支テーブル
(
    balance_id   BIGINT DEFAULT
                            nextval('kp.tbl_balance_seq'), -- 収支ID
    balance_name VARCHAR(2) NOT NULL,                      -- 収支名
    PRIMARY KEY (balance_id)
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
    balance_id                 BIGINT      NOT NULL,                                 -- 受取支払種別ID (支出 / 収入 / 振替)
    account_and_balance_id BIGINT,                                                   -- 口座ID (任意) (支出 / 振替: 送金元)
    memo                       TEXT,                                                 -- メモ (任意) 1000文字まで
    entry_date                 TIMESTAMPTZ NOT NULL,                                 -- 登録日時
    update_date                TIMESTAMPTZ,                                          -- 更新日時
    PRIMARY KEY (specification_group_id, user_id),
    FOREIGN KEY (user_id) REFERENCES kp.tbl_user (user_id),
    FOREIGN KEY (shop_id) REFERENCES kp.tbl_shop (shop_id),
    FOREIGN KEY (account_and_balance_id)
        REFERENCES kp.tbl_account_and_balance (account_and_balance_id),
    FOREIGN KEY (balance_id) REFERENCES kp.tbl_balance (balance_id)
);

CREATE SEQUENCE IF NOT EXISTS kp.tbl_specification_seq START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS kp.tbl_specification -- 明細テーブル
(
    specification_group_id BIGINT         NOT NULL,                                             -- 明細グループID
    specification_id       BIGINT
                                                   DEFAULT nextval('kp.tbl_specification_seq'), -- 明細ID
    user_id                BIGINT         NOT NULL,                                             -- ユーザID
    item_name              VARCHAR(255)   NOT NULL,                                             -- 商品名
    items_jpy_price        DECIMAL(10, 2) NOT NULL,                                             -- 価格 (日本円)
    currency_name          VARCHAR(3),                                                          -- 通貨名 (任意 : USD, EUR, ...)
    items_price            DECIMAL(10, 2),                                                      -- 価格 (任意 : 外貨)
    item_count             INTEGER        NOT NULL DEFAULT 1,                                   -- 商品点数
    memo                   TEXT,                                                                -- 1000文字まで                -- メモ (任意)
    entry_date             TIMESTAMPTZ    NOT NULL,                                             -- 登録日時
    update_date            TIMESTAMPTZ,                                                         -- 更新日時
    PRIMARY KEY (specification_group_id, specification_id, user_id),
    FOREIGN KEY (specification_group_id, user_id)
        REFERENCES kp.tbl_specification_group (specification_group_id, user_id)
);
