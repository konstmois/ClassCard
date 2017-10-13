INSERT INTO cc_user (id, login, password, description, role, deleted) VALUES (NEXTVAL('s_user_id'), 'test', 'test', 'Test Class Member', 'CLASS_MEMBER', false);
INSERT INTO cc_user (id, login, password, description, role, deleted) VALUES (NEXTVAL('s_user_id'), 'admin', 'admin', 'Admin', 'ADMIN', false);

INSERT INTO card (id, owner, number, balance, type, description) VALUES (NEXTVAL('s_card_id'), 1, '4517', 10000.00, 'VISA', 'Visa Classic');


INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -1500.00, 'Тетради',   '7395', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -2500.00, 'Учебники',  '7395', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -3500.00, 'Театр',     '7929', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -4500.00, 'Экскурсия', '7929', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -1000.00, 'Тетради',   '7395', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -2000.00, 'Учебники',  '5972', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -3000.00, 'Экскурсия', '5972', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'EXPENSE', -4000.00, 'Театр',     '5972', current_timestamp);


INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 2500.00, 'От Петр Петрович П.',      '2001', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 3500.00, 'От Анна Сергеевна П.',     '2002', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 4500.00, 'От Ирина Алексндровна У.', '2003', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 2000.00, 'От Ирина Петровна Б.',     '2001', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 3000.00, 'От Ирина Алексндровна У.', '2002', current_timestamp);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 4000.00, 'От Илья Игоревич И.',      '2003', current_timestamp);
