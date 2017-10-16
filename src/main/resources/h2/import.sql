INSERT INTO CARD (id, number, balance, type, description) VALUES (S_CARD_ID.NEXTVAL, '7771', 10000.00, 'VISA', 'Visa Classic');

INSERT INTO STUDENT_CLASS(id, name, card) VALUES (S_STUDENT_CLASS_ID.NEXTVAL, 'Школа №1, Класс 1А', 1);

INSERT INTO CC_USER (id, login, password, student_class, description, role, deleted) VALUES (S_USER_ID.NEXTVAL, 'test',  'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 1,'Test Class Member', 'CLASS_MEMBER', 0);
INSERT INTO CC_USER (id, login, password, student_class, description, role, deleted) VALUES (S_USER_ID.NEXTVAL, 'testm', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 1,'Test Class Manager', 'CLASS_MANAGER', 0);
INSERT INTO CC_USER (id, login, password, description, role, deleted) VALUES (S_USER_ID.NEXTVAL, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Admin', 'ADMIN', 0);

INSERT INTO TARGET (id, name, student_class, req_amount, active) VALUES (S_TARGET_ID.NEXTVAL, 'Учебники', 1, 8500.00,  1);
INSERT INTO TARGET (id, name, student_class, req_amount, active) VALUES (S_TARGET_ID.NEXTVAL, 'Поездки',  1, 50000.00, 1);
INSERT INTO TARGET (id, name, student_class, req_amount, active) VALUES (S_TARGET_ID.NEXTVAL, 'Обеды',    1, 10000.00, 1);
INSERT INTO TARGET (id, name, student_class, req_amount, active) VALUES (S_TARGET_ID.NEXTVAL, 'Выпускной',1, 10000.00, 0);

INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Петя', 'Иванов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Маша', 'Петрова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Коля', 'Серов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Оля',  'Смирнова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Вика', 'Сидорова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Игорь','Савинов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Анна', 'Варламова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Антон','Фомин', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Дарья','Маслова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Нина', 'Дурова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Витя', 'Фомичев', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Вася', 'Романов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Саша', 'Капустина', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Влада','Захарова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Вова', 'Краснов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Саша', 'Тихомиров', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Сережа','Попов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Женя', 'Петрова', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Дима', 'Максимов', 1);
INSERT INTO STUDENT(id, name, last_name, student_class) VALUES (S_STUDENT_ID.NEXTVAL, 'Рома', 'Саблин', 1);


/*Расходы*/
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 1, 'EXPENSE', -1500.00, 'Тетради',   '7395', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 1, 'EXPENSE', -2500.00, 'Учебники',  '7395', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 1, 'EXPENSE', -3500.00, 'Театр',     '7929', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 1, 'EXPENSE', -4500.00, 'Экскурсия', '7929', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 1, 'EXPENSE', -1000.00, 'Тетради',   '7395', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 1, 'EXPENSE', -2000.00, 'Учебники',  '5972', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 1, 'EXPENSE', -3000.00, 'Обеды',     '5972', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 1, 'EXPENSE', -4000.00, 'Обеды',     '5972', sysdate, 0);
/*INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'EXPENSE', -4000.00, 'Нет цели', '5972', sysdate, 0);*/

/*Нераспределенные приходные операции*/
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 2500.00, 'От Петр Петрович П.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 3500.00, 'От Анна Сергеевна П.',     '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 4500.00, 'От Ирина Алексндровна У.', '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 2000.00, 'От Ирина Петровна Б.',     '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 3000.00, 'От Ирина Алексндровна У.', '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 'INCOME', 4000.00, 'От Илья Игоревич И.',      '2003', sysdate, 0);

/*Приходы на цель 1*/
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 1,  1, 'INCOME', 500.00,  'От Петр Петрович П.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 3,  1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 5,  1, 'INCOME', 1500.00, 'От Анна Сергеевна П.',     '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 7,  1, 'INCOME', 500.00,  'От Ирина Алексндровна У.', '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 12, 1, 'INCOME', 300.00,  'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 4,  1, 'INCOME', 800.00,  'От Ирина Петровна Б.',     '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 8,  1, 'INCOME', 900.00,  'От Ирина Алексндровна У.', '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 17, 1, 'INCOME', 1000.00, 'От Илья Игоревич И.',      '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 14, 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 18, 1, 'INCOME', 1200.00, 'От Ирина Петровна Б.',     '2001', sysdate, 0);

/*Приходы на цель 2*/
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 2,  1, 'INCOME', 3500.00, 'От Петр Петрович П.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 7,  1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 9,  1, 'INCOME', 1500.00, 'От Анна Сергеевна П.',     '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 16, 1, 'INCOME', 500.00,  'От Ирина Алексндровна У.', '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 12, 1, 'INCOME', 1300.00, 'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 13, 1, 'INCOME', 3800.00, 'От Ирина Петровна Б.',     '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 5,  1, 'INCOME', 900.00,  'От Ирина Алексндровна У.', '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 10, 1, 'INCOME', 1000.00, 'От Илья Игоревич И.',      '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 11, 1, 'INCOME', 1000.00, 'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 15, 1, 'INCOME', 1200.00, 'От Ирина Петровна Б.',     '2001', sysdate, 0);

/*Приходы на цель 3*/
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 8,  1, 'INCOME', 500.00,  'От Петр Петрович П.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 1,  1, 'INCOME', 2500.00, 'От Илья Игоревич И.',      '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 14, 1, 'INCOME', 1500.00, 'От Анна Сергеевна П.',     '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 9,  1, 'INCOME', 500.00,  'От Ирина Алексндровна У.', '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 10, 1, 'INCOME', 500.00,  'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 11, 1, 'INCOME', 1800.00, 'От Ирина Петровна Б.',     '2001', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 5,  1, 'INCOME', 1000.00, 'От Ирина Алексндровна У.', '2002', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 5,  1, 'INCOME', 2000.00, 'От Илья Игоревич И.',      '2003', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 15, 1, 'INCOME', 3000.00, 'От Ирина Петровна Б.',     '2000', sysdate, 0);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 19, 1, 'INCOME', 1200.00, 'От Ирина Петровна Б.',     '2001', sysdate, 0);

/*Сдача по каждой из целей*/
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 3, 8,  1, 'INCOME', 500.00,  'Сдача 3',      '2001', sysdate, 1);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 2, 8,  1, 'INCOME', 500.00,  'Сдача 2',      '2001', sysdate, 1);
INSERT INTO CARD_OPERATION (id, target, student, card, type, amount, description, mcc, date, is_rest) VALUES (S_CARD_OP_ID.NEXTVAL, 1, 8,  1, 'INCOME', 500.00,  'Сдача 1',      '2001', sysdate, 1);