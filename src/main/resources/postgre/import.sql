INSERT INTO card (id, number, balance, type, description) VALUES (NEXTVAL('s_card_id'), '7771', 10000.00, 'VISA', 'Visa Classic');

INSERT INTO student_class(id, name, card) VALUES (NEXTVAL('s_student_class_id'), 'Школа №1, Класс 1А', 1);

INSERT INTO cc_user (id, login, password, student_class, description, role, deleted) VALUES (NEXTVAL('s_user_id'), 'test',  'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 1,'Test Class Member', 'CLASS_MEMBER', false);
INSERT INTO cc_user (id, login, password, student_class, description, role, deleted) VALUES (NEXTVAL('s_user_id'), 'testm', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 1,'Test Class Manager','CLASS_MANAGER', false);
INSERT INTO cc_user (id, login, password, description, role, deleted) VALUES (NEXTVAL('s_user_id'), 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Admin', 'ADMIN', false);

INSERT INTO target (id, name, student_class, req_amount, active) VALUES (NEXTVAL('s_target_id'), 'Учебники', 1, 8500.00,  true);
INSERT INTO target (id, name, student_class, req_amount, active) VALUES (NEXTVAL('s_target_id'), 'Поездки',  1, 50000.00, true);
INSERT INTO target (id, name, student_class, req_amount, active) VALUES (NEXTVAL('s_target_id'), 'Обеды',    1, 10000.00, true);
INSERT INTO target (id, name, student_class, req_amount, active) VALUES (NEXTVAL('s_target_id'), 'Выпускной',1, 10000.00, false);

INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Петя', 'Иванов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Маша', 'Петрова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Коля', 'Серов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Оля',  'Смирнова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Вика', 'Сидорова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Игорь','Савинов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Анна', 'Варламова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Антон','Фомин', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Дарья','Маслова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Нина', 'Дурова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Витя', 'Фомичев', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Вася', 'Романов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Саша', 'Капустина', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Влада','Захарова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Вова', 'Краснов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Саша', 'Тихомиров', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Сережа','Попов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Женя', 'Петрова', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Дима', 'Максимов', 1);
INSERT INTO student(id, name, last_name, student_class) VALUES (NEXTVAL('s_student_id'), 'Рома', 'Саблин', 1);

/*Расходы*/
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 1, 'EXPENSE', -1500.00, 'Тетради',   '7395', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 1, 'EXPENSE', -2500.00, 'Учебники',  '7395', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 1, 'EXPENSE', -3500.00, 'Театр',     '7929', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 1, 'EXPENSE', -4500.00, 'Экскурсия', '7929', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 1, 'EXPENSE', -1000.00, 'Тетради',   '7395', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 1, 'EXPENSE', -2000.00, 'Учебники',  '5972', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 1, 'EXPENSE', -3000.00, 'Обеды',     '5972', current_timestamp, false);
INSERT INTO card_operation (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 1, 'EXPENSE', -4000.00, 'Обеды',     '5972', current_timestamp, false);
/*INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'EXPENSE', -4000.00, 'Нет цели', '5972', sysdate, 0);*/

/*Нераспределенные приходные операции*/
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 2500.00, 'От Петр Петрович П.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 3500.00, 'От Анна Сергеевна П.',     '2002', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 4500.00, 'От Ирина Алексндровна У.', '2003', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 2000.00, 'От Ирина Петровна Б.',     '2001', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 3000.00, 'От Ирина Алексндровна У.', '2002', current_timestamp, false);
INSERT INTO card_operation (id, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 'INCOME', 4000.00, 'От Илья Игоревич И.',      '2003', current_timestamp, false);

/*Приходы на цель 1*/
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 1,  1, 'INCOME', 500.00,  'От Петр Петрович П.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 3,  1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 5,  1, 'INCOME', 1500.00, 'От Анна Сергеевна П.',     '2002', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 7,  1, 'INCOME', 500.00,  'От Ирина Алексндровна У.', '2003', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 12, 1, 'INCOME', 300.00,  'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 4,  1, 'INCOME', 800.00,  'От Ирина Петровна Б.',     '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 8,  1, 'INCOME', 900.00,  'От Ирина Алексндровна У.', '2002', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 17, 1, 'INCOME', 1000.00, 'От Илья Игоревич И.',      '2003', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 14, 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 18, 1, 'INCOME', 1200.00, 'От Ирина Петровна Б.',     '2001', current_timestamp, false);

/*Приходы на цель 2*/
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 2,  1, 'INCOME', 3500.00, 'От Петр Петрович П.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 7,  1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 9,  1, 'INCOME', 1500.00, 'От Анна Сергеевна П.',     '2002', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 16, 1, 'INCOME', 500.00,  'От Ирина Алексндровна У.', '2003', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 12, 1, 'INCOME', 1300.00, 'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 13, 1, 'INCOME', 3800.00, 'От Ирина Петровна Б.',     '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 5,  1, 'INCOME', 900.00,  'От Ирина Алексндровна У.', '2002', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 10, 1, 'INCOME', 1000.00, 'От Илья Игоревич И.',      '2003', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 11, 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 15, 1, 'INCOME', 1200.00, 'От Ирина Петровна Б.',     '2001', current_timestamp, false);

/*Приходы на цель 3*/
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 8,  1, 'INCOME', 500.00,  'От Петр Петрович П.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 1,  1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 14, 1, 'INCOME', 1500.00, 'От Анна Сергеевна П.',     '2002', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 9,  1, 'INCOME', 500.00,  'От Ирина Алексндровна У.', '2003', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 10, 1, 'INCOME', 500.00,  'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 11, 1, 'INCOME', 1800.00, 'От Ирина Петровна Б.',     '2001', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 5,  1, 'INCOME', 1000.00, 'От Ирина Алексндровна У.', '2002', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 5,  1, 'INCOME', 2000.00, 'От Илья Игоревич И.',      '2003', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 15, 1, 'INCOME', 3000.00, 'От Ирина Петровна Б.',     '2000', current_timestamp, false);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 19, 1, 'INCOME', 1200.00, 'От Ирина Петровна Б.',     '2001', current_timestamp, false);

/*Сдача по каждой из целей*/
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 3, 8,  1, 'INCOME', 500.00,  'Сдача 3',      '2001', current_timestamp, true);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 2, 8,  1, 'INCOME', 500.00,  'Сдача 2',      '2001', current_timestamp, true);
INSERT INTO card_operation (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (NEXTVAL('s_card_op_id'), 1, 8,  1, 'INCOME', 500.00,  'Сдача 1',      '2001', current_timestamp, true);