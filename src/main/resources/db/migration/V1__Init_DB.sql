CREATE TABLE ailp.users
(
  user_id     BIGINT NOT NULL,
  plan_id     BIGINT,
  first_name  VARCHAR(150),
  last_name   VARCHAR(150),
  username    VARCHAR(150),
  password    VARCHAR(150),
  create_date TIMESTAMP(0) WITHOUT TIME ZONE,
  is_active   BOOLEAN,
  PRIMARY KEY (user_id)
);

COMMENT ON TABLE ailp.users
  IS 'Пользователи';

COMMENT ON COLUMN ailp.users.user_id
  IS 'ID пользователя';

COMMENT ON COLUMN ailp.users.plan_id
  IS 'id плана занятий';

COMMENT ON COLUMN ailp.users.first_name
  IS 'Имя';

COMMENT ON COLUMN ailp.users.last_name
  IS 'Фамилия';

COMMENT ON COLUMN ailp.users.username
  IS 'Логин';

COMMENT ON COLUMN ailp.users.password
  IS 'Пароль';

COMMENT ON COLUMN ailp.users.create_date
  IS 'Дата создания пользователя';

COMMENT ON COLUMN ailp.users.is_active
  IS 'Активность пользователя';

CREATE TABLE ailp.plans
(
  plan_id    BIGINT NOT NULL,
  start_date TIMESTAMP(0) WITHOUT TIME ZONE,
  end_date   TIMESTAMP(0) WITHOUT TIME ZONE,
  lesson_1   BIGINT,
  lesson_2   BIGINT,
  lesson_3   BIGINT,
  PRIMARY KEY (plan_id)
);

COMMENT ON TABLE ailp.plans
  IS 'Планы занятий';

COMMENT ON COLUMN ailp.plans.plan_id
  IS 'ID плана';

COMMENT ON COLUMN ailp.plans.start_date
  IS 'Дата начала действия плана';

COMMENT ON COLUMN ailp.plans.end_date
  IS 'Дата окончания действия плана';

COMMENT ON COLUMN ailp.plans.lesson_1
  IS 'Первый урок';

COMMENT ON COLUMN ailp.plans.lesson_2
  IS 'Второй урок';

COMMENT ON COLUMN ailp.plans.lesson_3
  IS 'Третий урок';

CREATE TABLE ailp.event_logs
(
  event_log_id BIGINT NOT NULL,
  user_id      BIGINT,
  event_id     BIGINT,
  event_date   TIMESTAMP(0) WITHOUT TIME ZONE,
  page_id      BIGINT,
  lesson_id    BIGINT,
  PRIMARY KEY (event_log_id)
);

COMMENT ON TABLE ailp.event_logs
  IS 'Таблица логов событий';

COMMENT ON COLUMN ailp.event_logs.event_log_id
  IS 'ID записи (суррогатный ключ)';

COMMENT ON COLUMN ailp.event_logs.user_id
  IS 'ID пользователя';

COMMENT ON COLUMN ailp.event_logs.event_id
  IS 'ID События';

COMMENT ON COLUMN ailp.event_logs.event_date
  IS 'Дата время события';

COMMENT ON COLUMN ailp.event_logs.page_id
  IS 'Страница на котором было событие';

COMMENT ON COLUMN ailp.event_logs.lesson_id
  IS 'Урок на котором было событие';

CREATE TABLE ailp.events
(
  event_id   BIGINT NOT NULL,
  event_type VARCHAR(100),
  event_name VARCHAR(100),
  start_date TIMESTAMP(0) WITHOUT TIME ZONE,
  end_date   TIMESTAMP(0) WITHOUT TIME ZONE,
  PRIMARY KEY (event_id)
);

COMMENT ON TABLE ailp.events
  IS 'События';

COMMENT ON COLUMN ailp.events.event_id
  IS 'ID события';

COMMENT ON COLUMN ailp.events.event_type
  IS 'Тип события';

COMMENT ON COLUMN ailp.events.event_name
  IS 'Имя события';

COMMENT ON COLUMN ailp.events.start_date
  IS 'Дата начала действия события';

COMMENT ON COLUMN ailp.events.end_date
  IS 'Дата окончания действия события';

create table ailp.user_role
(
  user_id BIGINT not null,
  roles   varchar(255)
);

COMMENT ON COLUMN ailp.user_role.user_id
  IS 'ID пользователя';

COMMENT ON COLUMN ailp.user_role.roles
  IS 'Роль';

CREATE TABLE ailp.lessons
(
  lesson_id   BIGINT NOT NULL,
  lesson_name text,
  start_date  TIMESTAMP(0) WITHOUT TIME ZONE,
  end_date    TIMESTAMP(0) WITHOUT TIME ZONE,
  PRIMARY KEY (lesson_id)
);

COMMENT ON TABLE ailp.lessons
  IS 'Таблица уроков';

COMMENT ON COLUMN ailp.lessons.lesson_id
  IS 'ID записи (суррогатный ключ)';

COMMENT ON COLUMN ailp.lessons.lesson_name
  IS 'Наименование урока';
COMMENT ON COLUMN ailp.lessons.start_date
  IS 'Дата начала действия урока';

COMMENT ON COLUMN ailp.lessons.end_date
  IS 'Дата окончания действия урока';

CREATE TABLE ailp.lesson_tests
(
  lesson_test_id BIGINT NOT NULL,
  lesson_id      BIGINT,
  test_id        BIGINT,
  test_type_id   BIGINT,
  start_date     TIMESTAMP(0) WITHOUT TIME ZONE,
  end_date       TIMESTAMP(0) WITHOUT TIME ZONE,
  PRIMARY KEY (lesson_test_id)
);

COMMENT ON TABLE ailp.lesson_tests
  IS 'Таблица тестов по урокам';

COMMENT ON COLUMN ailp.lesson_tests.lesson_test_id
  IS 'ID записи (суррогатный ключ)';

COMMENT ON COLUMN ailp.lesson_tests.lesson_id
  IS 'ID урока';

COMMENT ON COLUMN ailp.lesson_tests.test_id
  IS 'ID теста';

COMMENT ON COLUMN ailp.lesson_tests.test_type_id
  IS 'ID типа теста';

COMMENT ON COLUMN ailp.lesson_tests.start_date
  IS 'Дата начала действия теста по уроку';

COMMENT ON COLUMN ailp.lesson_tests.end_date
  IS 'Дата окончания действия теста по уроку';


CREATE SEQUENCE IF NOT EXISTS ailp.events_event_id_seq START 1;
alter table ailp.events
  ALTER column event_id SET DEFAULT nextval('ailp.events_event_id_seq');

CREATE SEQUENCE IF NOT EXISTS ailp.event_logs_event_log_id_seq START 1;
alter table ailp.event_logs
  ALTER column event_log_id SET DEFAULT nextval('ailp.event_logs_event_log_id_seq');

CREATE SEQUENCE IF NOT EXISTS ailp.users_user_id_seq START 1;
alter table ailp.users
  ALTER column user_id SET DEFAULT nextval('ailp.users_user_id_seq');

CREATE SEQUENCE IF NOT EXISTS ailp.plans_plan_id_seq START 1;
alter table ailp.plans
  ALTER column plan_id SET DEFAULT nextval('ailp.plans_plan_id_seq');

CREATE SEQUENCE IF NOT EXISTS ailp.lessons_lesson_id_seq START 1;
alter table ailp.lessons
  ALTER column lesson_id SET DEFAULT nextval('ailp.lessons_lesson_id_seq');

CREATE SEQUENCE IF NOT EXISTS ailp.lesson_tests_lesson_test_id_seq START 1;
alter table ailp.lesson_tests
  ALTER column lesson_test_id SET DEFAULT nextval('ailp.lesson_tests_lesson_test_id_seq');
