insert into PUBLIC.USR(USER_ID, NICK_NAME, LAST_NAME, FIRST_NAME, PASSWORD, EMAIL, BIRTHDAY, PHONE_NUMBER, ROLE_NAME,
                       ENTRY_DATE, UPDATE_DATE)
values (0, 'juiceA', 'Hoge', 'Fuga', 'test', 'juiceA@example.com', TO_DATE('2024-01-01', 'YYYY-MM-DD'), '00011112222',
        'ADMIN', TIMESTAMP '2024-07-19 00:00:00', NULL);
insert into PUBLIC.USR(USER_ID, NICK_NAME, LAST_NAME, FIRST_NAME, PASSWORD, EMAIL, BIRTHDAY, PHONE_NUMBER, ROLE_NAME,
                       ENTRY_DATE, UPDATE_DATE)
values ((select max(user_id) from USR) + 1, 'juiceB', 'Foo', 'Bar', 'test', 'juiceB@example.com',
        TO_DATE('2023-12-31', 'YYYY-MM-DD'), '00011112222', 'USER', TIMESTAMP '2024-07-20 00:00:00', NULL);