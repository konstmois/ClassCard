CREATE TABLE "card" (
  "id"          BIGINT PRIMARY KEY,
  "number"      VARCHAR(10),
  "balance"     DECIMAL,
  "description" VARCHAR(100),
  "type"        VARCHAR(50)
);
CREATE UNIQUE INDEX idx_card_id ON "card"(id);
CREATE SEQUENCE s_card_id;




CREATE TABLE "student_class" (
  "id"   BIGINT PRIMARY KEY,
  "name" VARCHAR(100),
  "card" BIGINT REFERENCES "card"(id)
);
CREATE UNIQUE INDEX idx_student_class_id   ON "student_class"(id);
CREATE        INDEX idx_student_class_card ON "student_class"(card);
CREATE SEQUENCE s_student_class_id;




CREATE TABLE "cc_user" (
  "id"            BIGINT PRIMARY KEY,
  "login"         VARCHAR(50),
  "password"      VARCHAR(100),
  "description"   VARCHAR(100),
  "deleted"       BOOLEAN,
  "role"          VARCHAR(20),
  "student_class" BIGINT REFERENCES "student_class"(id)
);
CREATE UNIQUE INDEX idx_user_id    ON "cc_user"(id);
CREATE UNIQUE INDEX idx_user_login ON "cc_user"(login);
CREATE        INDEX idx_user_class ON "cc_user"(student_class);
CREATE SEQUENCE s_user_id;




CREATE TABLE "target" (
  "id"            BIGINT PRIMARY KEY,
  "name"          VARCHAR(100),
  "req_amount"    DECIMAL,
  "student_class" BIGINT REFERENCES "student_class"(id),
  "active"        BOOLEAN
);
CREATE UNIQUE INDEX idx_target_id    ON "target"(id);
CREATE        INDEX idx_target_class ON "target"(student_class);
CREATE SEQUENCE s_target_id;




CREATE TABLE "student" (
  "id"            BIGINT PRIMARY KEY,
  "name"          VARCHAR(100),
  "last_name"     VARCHAR(100),
  "student_class" BIGINT REFERENCES "student_class"(id)
);
CREATE UNIQUE INDEX idx_student_id    ON "student"(id);
CREATE        INDEX idx_student_class ON "student"(student_class);
CREATE SEQUENCE s_student_id;



CREATE TABLE "card_operation" (
  "id"          BIGINT PRIMARY KEY,
  "card"        BIGINT REFERENCES "card"(id),
  "student"     BIGINT REFERENCES "student"(id),
  "target"      BIGINT REFERENCES "target"(id),
  "type"        VARCHAR(50),
  "amount"      DECIMAL,
  "description" VARCHAR(100),
  "date"        TIMESTAMP,
  "mcc"         VARCHAR(10),
  "is_rest"     BOOLEAN
);
CREATE UNIQUE INDEX idx_card_op_id      ON "card_operation"(id);
CREATE        INDEX idx_card_op_card    ON "card_operation"(card);
CREATE        INDEX idx_card_op_target  ON "card_operation"(target);
CREATE        INDEX idx_card_op_student ON "card_operation"(student);
CREATE SEQUENCE s_card_op_id;

