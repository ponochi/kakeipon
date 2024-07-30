INSERT INTO tbl_role(role_name) VALUES ('ADMIN');
INSERT INTO tbl_role(role_name) VALUES ('USER');

INSERT INTO tbl_user(nick_name, last_name, first_name, password, email,
                     birthday, phone_number, role_id, entry_date, update_date)
values ('juiceA', 'Foo1', 'Bar1', 'test', 'juiceA@example.com',
        '1971-12-31 00:00:00', '00011112222', 1, '2024-07-19 00:00:00', NULL);
INSERT INTO tbl_user(nick_name, last_name, first_name, password, email,
                     birthday, phone_number, role_id, entry_date, update_date)
values ('juiceB', 'Foo2', 'Bar2', 'test',
        'juiceB@example.com', '1970-01-01 00:00:00', '00011112222', 2,
        '2024-07-20 00:00:00', NULL);
