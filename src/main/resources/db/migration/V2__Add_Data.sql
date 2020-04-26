insert into ailp.users(username, password, is_active)
VALUES ('admin', '1', true);

insert into ailp.event_logs(event_id, lesson_id, page_id, user_id)
VALUES (1, 1, 1, 1);

insert into ailp.events(event_type, event_name)
VALUES ('click', 'back');

insert into ailp.plans(lesson_1, lesson_2, lesson_3)
VALUES (1,2,3);

insert into ailp.user_role(user_id,roles)
VALUES (1, 'ADMIN');

insert into ailp.lessons(lesson_name)
VALUES ('Вводная');

insert into ailp.lesson_tests(test_id, test_type_id)
VALUES (1, 1);
