CREATE TABLE "public.users" (
	"user_id" serial NOT NULL,
	"user_role" integer NOT NULL,
	"user_login" varchar(50) NOT NULL,
	"user_passwd" varchar(250) NOT NULL,
	"user_mark" varchar(250) NOT NULL,
	CONSTRAINT "users_pk" PRIMARY KEY ("user_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "public.tasks" (
	"task_id" serial NOT NULL,
	"emp_id_employee" integer NOT NULL,
	"task_type" integer NOT NULL,
	"task_name" varchar(50) NOT NULL,
	"task_date_from" DATE NOT NULL,
	"task_date_to" DATE NOT NULL,
	"task_todo" varchar(1200),
	"task_note" varchar(1200),
	"task_is_compl" BOOLEAN NOT NULL DEFAULT 'false',
	CONSTRAINT "tasks_pk" PRIMARY KEY ("task_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "public.employee" (
	"emp_id" serial NOT NULL,
	"user_id_users" integer NOT NULL,
	"emp_surname" varchar(50) NOT NULL,
	"emp_name" varchar(50) NOT NULL,
	"emp_mid_name" varchar(50) NOT NULL,
	"pos_id_position" integer NOT NULL,
	CONSTRAINT "employee_pk" PRIMARY KEY ("emp_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "public.positions" (
	"position_id" serial NOT NULL,
	"position" integer NOT NULL,
	CONSTRAINT "Positions_pk" PRIMARY KEY ("position_id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "tasks" ADD CONSTRAINT "tasks_fk0" FOREIGN KEY ("emp_id_employee") REFERENCES "employee"("emp_id") ON DELETE CASCADE;
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("user_id_users") REFERENCES "users"("user_id") ON DELETE CASCADE;
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk1" FOREIGN KEY ("pos_id_position") REFERENCES "positions"("position_id") ON DELETE CASCADE;

INSERT INTO users (user_role, user_login, user_passwd, user_mark) VALUES (1, 'Admin', '423A89387AE2B8C81395E70F4488A62B698A967DF2DFA5430B62E1CEDF8112C649714F1A935A8AFE54B5EA6670C92F4EA06A7DA208BCF8CC6860851E083E1493', 'wpJOantWKn');


CREATE OR REPLACE VIEW public.emp_join_task
    AS
     SELECT employee.emp_id,
    employee.emp_surname,
    employee.emp_name,
    employee.emp_mid_name,
    employee.pos_id_position,
    tasks.task_id,
    tasks.emp_id_employee,
    tasks.task_type,
    tasks.task_name,
    tasks.task_date_from,
    tasks.task_date_to,
    tasks.task_todo,
    tasks.task_note,
    tasks.task_is_compl
   FROM employee
     JOIN tasks ON employee.emp_id = tasks.emp_id_employee;

CREATE OR REPLACE VIEW public.user_join_three
    AS
     SELECT users.user_id,
    users.user_role,
    users.user_login,
    users.user_passwd,
    users.user_mark,
    employee.emp_id,
    employee.user_id_users,
    employee.emp_surname,
    employee.emp_name,
    employee.emp_mid_name,
    employee.pos_id_position,
    positions.position_id,
    positions."position"
   FROM users
     JOIN (employee
     JOIN positions ON employee.pos_id_position = positions.position_id) ON users.user_id = employee.user_id_users;

CREATE OR REPLACE VIEW public.task
    AS
     SELECT tasks.task_id,
    tasks.emp_id_employee,
    tasks.task_type,
    tasks.task_name,
    tasks.task_date_from,
    tasks.task_date_to,
    tasks.task_todo,
    tasks.task_note,
    tasks.task_is_compl
   FROM tasks;

CREATE OR REPLACE RULE "INSERT" AS
    ON INSERT TO public.user_join_three
    DO INSTEAD
( INSERT INTO users (user_role, user_login, user_passwd, user_mark)
  VALUES (new.user_role, new.user_login, new.user_passwd, new.user_mark);
 INSERT INTO employee (emp_surname, emp_name, emp_mid_name, pos_id_position)
  VALUE (new.emp_surname, new.emp_name, new.emp_mid_name, new.pos_id_position);
);

CREATE OR REPLACE RULE "UPDATE" AS
    ON UPDATE TO public.user_join_three
    DO INSTEAD
( UPDATE users SET user_role = new.user_role, user_login = new.user_login, user_passwd = new.user_passwd, user_mark = new.user_mark;
 UPDATE employee SET emp_surname = new.emp_surname, emp_name = new.emp_name, emp_mid_name = new.emp_mid_name, pos_id_position = new.pos_id_position;
);











