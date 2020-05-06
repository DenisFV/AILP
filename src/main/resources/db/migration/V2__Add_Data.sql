insert into ailp.users(username, password, is_active)
VALUES ('admin', '1', true);

insert into ailp.event_logs(event_id, lesson_id, page_id, user_id)
VALUES (1, 1, 1, 1);

insert into ailp.events(event_type, event_name)
VALUES ('click', 'back');

insert into ailp.plans(lesson_1, lesson_2, lesson_3)
VALUES (1, 2, 3);

insert into ailp.user_role(user_id, roles)
VALUES (1, 'ADMIN');

insert into ailp.lessons(lesson_name)
VALUES ('Вводная');

insert into ailp.lesson_tests(lesson_id, page_id, test_id, test_type_id, test_name)
VALUES (1, 2, 1, 1, 'First Test');

Insert into ailp.test_results(test_id, question_id, user_id, result, comment)
values (1, 1, 1, 'True', 'test row');

Insert into ailp.pages(lesson_id, page_name, page_type, comment)
values (2, 'Second page', 'Test', 'test row');

Insert into ailp.questions(test_id, answer_option, question, comment)
values (1, 'one', 'Верите ли вы в бога?', 'все ок');

Insert into ailp.questions(test_id, answer_option, question, comment)
values (1, 'mult', 'Хотите покушать?', 'все ок');

Insert into ailp.questions(test_id, answer_option, question, comment)
values (1, 'text', 'Что кушал утром?', 'все ок');

Insert into ailp.question_answers(question_id, answer, comment)
values (1, 'Да', 'все ок');

Insert into ailp.question_answers(question_id, answer, comment)
values (1, 'Нет', 'все ок');

Insert into ailp.question_answers(question_id, answer, comment)
values (1, 'Хз', 'все ок');

Insert into ailp.question_answers(question_id, answer, comment)
values (2, 'Да, хочу', 'все ок');

Insert into ailp.question_answers(question_id, answer, comment)
values (2, 'Нет, не хочу', 'все ок');

Insert into ailp.question_answers(question_id, answer, comment)
values (2, 'ХЗ', 'все ок');

Insert into ailp.question_answers(question_id, comment)
values (3, 'все ок');