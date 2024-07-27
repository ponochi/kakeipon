insert into PUBLIC.USR(USER_ID, NICK_NAME, LAST_NAME, FIRST_NAME, PASSWORD, EMAIL, BIRTHDAY, PHONE_NUMBER, ROLE_NAME,
                       ENTRY_DATE, UPDATE_DATE)
values (0, 'juiceA', 'Hoge', 'Fuga', 'test', 'juiceA@example.com',
        TIMESTAMP '1970-01-01 00:00:00', '00011112222', 'ADMIN', TIMESTAMP '2024-07-19 00:00:00', NULL);
insert into PUBLIC.USR(USER_ID, NICK_NAME, LAST_NAME, FIRST_NAME, PASSWORD, EMAIL, BIRTHDAY, PHONE_NUMBER, ROLE_NAME,
                       ENTRY_DATE, UPDATE_DATE)
values ((select max(user_id) from USR) + 1, 'juiceB', 'Foo', 'Bar', 'test', 'juiceB@example.com',
        TIMESTAMP '1970-01-01 00:00:00', '00011112222', 'USER', TIMESTAMP '2024-07-20 00:00:00', NULL);
