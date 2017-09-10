CREATE TABLE "cc_user" (
  "id"          BIGINT PRIMARY KEY,
  "login"       VARCHAR(50),
  "password"    VARCHAR(100),
  "description" VARCHAR(100),
  "deleted"     BOOLEAN,
  "role"        VARCHAR(20)
);
CREATE UNIQUE INDEX idx_user_id ON "cc_user"(id);
CREATE UNIQUE INDEX idx_user_login ON "cc_user"(login);
CREATE SEQUENCE s_user_id;




CREATE TABLE "card" (
  "id"          BIGINT PRIMARY KEY,
  "number"      VARCHAR(10),
  "balance"     DECIMAL,
  "description" VARCHAR(100),
  "owner"       BIGINT REFERENCES "cc_user"(id),
  "type"        VARCHAR(50)
);
CREATE UNIQUE INDEX idx_card_id ON "card"(id);
CREATE INDEX idx_card_owner ON "card"(owner);
CREATE SEQUENCE s_card_id;




CREATE TABLE "card_operation" (
  "id"          BIGINT PRIMARY KEY,
  "card"        BIGINT REFERENCES "card"(id),
  "type"        VARCHAR(50),
  "amount"      DECIMAL,
  "description" VARCHAR(100),
  "date"        TIMESTAMP,
  "mcc"         VARCHAR(10)
);
CREATE UNIQUE INDEX idx_card_op_id ON "card_operation"(id);
CREATE INDEX idx_card_op_card ON "card"(id);
CREATE SEQUENCE s_card_op_id;




CREATE TABLE "expense_category" (
  "id"          BIGINT PRIMARY KEY,
  "description" VARCHAR(100)
);
CREATE UNIQUE INDEX idx_exp_cat_id ON "expense_category"(id);
CREATE SEQUENCE s_expense_id;




CREATE TABLE "mcc_code" (
  "id"       BIGINT PRIMARY KEY,
  "code"     VARCHAR(100) UNIQUE,
  "category" BIGINT REFERENCES "expense_category"(id)
);
CREATE UNIQUE INDEX idx_mcc_code_id ON "mcc_code"(id);
CREATE UNIQUE INDEX idx_mcc_code_code ON "mcc_code"(code);
CREATE SEQUENCE s_mcc_code_id;






