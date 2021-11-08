INSERT INTO public.address (id, city, country, number, post_code, street, is_active, created_at) VALUES (1, 'Brussels', 'Belgium', 50, 1000, 'rue Sans Souci', true, '2021-10-20');
INSERT INTO public.address (id, city, country, number, post_code, street, is_active, created_at) VALUES (2, 'Liege', 'Belgium', 27, 5000, 'rue de Liege', true, '2021-10-20');
INSERT INTO public.address (id, city, country, number, post_code, street, is_active, created_at) VALUES (3, 'Anvers', 'Belgium', 78, 2400, 'Grote Markt', true, '2021-10-20');
INSERT INTO public.address (id, city, country, number, post_code, street, is_active, created_at) VALUES (4, 'Brussels', 'Belgium', 45, 2700, 'Rue du Trone', true, '2021-10-20');

INSERT INTO public.role (id, label) VALUES (4, 'ROLE_SEE_ALERTS');
INSERT INTO public.role (id, label) VALUES (2, 'ROLE_CREATE_EVENT');
INSERT INTO public.role (id, label) VALUES (1, 'ROLE_RAISE_ALERT');
INSERT INTO public.role (id, label) VALUES (3, 'ROLE_SEE_PARTICIPANTS');
INSERT INTO public.role (id, label) VALUES (5, 'ROLE_SEE_EVENTS');
INSERT INTO public.role (id, label) VALUES (6, 'ROLE_MANAGE_ALERTS');
INSERT INTO public.role (id, label) VALUES (7, 'ROLE_MANAGE_OWNED_ELEMENTS');
INSERT INTO public.role (id, label) VALUES (8, 'ROLE_MANAGE_TOPICS');
INSERT INTO public.role (id, label) VALUES (9, 'ROLE_MANAGE_EVENTS');
INSERT INTO public.role (id, label) VALUES (10, 'ROLE_CREATE_ADDRESS');
INSERT INTO public.role (id, label) VALUES (11, 'ROLE_MANAGE_USERS');
INSERT INTO public.role (id, label) VALUES (12, 'ROLE_CREATE_INVITATION');
INSERT INTO public.role (id, label) VALUES (13, 'ROLE_CREATE_TOPIC');

INSERT INTO public.security_group (id, label) VALUES (1, 'User');
INSERT INTO public.security_group (id, label) VALUES (2, 'Admin');

INSERT INTO public.role (id, label) VALUES (4, 'ROLE_SEE_ALERTS');
INSERT INTO public.role (id, label) VALUES (2, 'ROLE_CREATE_EVENT');
INSERT INTO public.role (id, label) VALUES (1, 'ROLE_RAISE_ALERT');
INSERT INTO public.role (id, label) VALUES (3, 'ROLE_SEE_PARTICIPANTS');
INSERT INTO public.role (id, label) VALUES (5, 'ROLE_SEE_EVENTS');
INSERT INTO public.role (id, label) VALUES (6, 'ROLE_MANAGE_ALERTS');
INSERT INTO public.role (id, label) VALUES (7, 'ROLE_MANAGE_OWNED_ELEMENTS');
INSERT INTO public.role (id, label) VALUES (8, 'ROLE_MANAGE_TOPICS');
INSERT INTO public.role (id, label) VALUES (9, 'ROLE_MANAGE_EVENTS');
INSERT INTO public.role (id, label) VALUES (10, 'ROLE_CREATE_ADDRESS');
INSERT INTO public.role (id, label) VALUES (11, 'ROLE_MANAGE_USERS');
INSERT INTO public.role (id, label) VALUES (12, 'ROLE_CREATE_INVITATION');
INSERT INTO public.role (id, label) VALUES (13, 'ROLE_CREATE_TOPIC');

INSERT INTO public.department (id, label) VALUES (1, 'Production');
INSERT INTO public.department (id, label) VALUES (2, 'Research');
INSERT INTO public.department (id, label) VALUES (3, 'Purchasing');
INSERT INTO public.department (id, label) VALUES (4, 'Marketing');
INSERT INTO public.department (id, label) VALUES (5, 'Human Resource Management');
INSERT INTO public.department (id, label) VALUES (6, 'Accounting and Finance');
INSERT INTO public.department (id, label) VALUES (7, 'Sales');
INSERT INTO public.department (id, label) VALUES (8, 'Customer Service');

INSERT INTO public.department (id, label) VALUES (9, 'IT');
INSERT INTO public.team (id, department_id, label) VALUES (1, 1, 'Norms');
INSERT INTO public.team (id, department_id, label) VALUES (2, 1, 'Productivity');
INSERT INTO public.team (id, department_id, label) VALUES (3, 1, 'Safety');
INSERT INTO public.team (id, department_id, label) VALUES (4, 2, 'Data analysis');
INSERT INTO public.team (id, department_id, label) VALUES (5, 2, 'Previsions');
INSERT INTO public.team (id, department_id, label) VALUES (6, 2, 'Innovation');
INSERT INTO public.team (id, department_id, label) VALUES (7, 3, 'Prospection');
INSERT INTO public.team (id, department_id, label) VALUES (8, 3, 'Relations');
INSERT INTO public.team (id, department_id, label) VALUES (9, 4, 'Digital');
INSERT INTO public.team (id, department_id, label) VALUES (10, 4, 'Social Networks');
INSERT INTO public.team (id, department_id, label) VALUES (11, 5, 'Recruitment');
INSERT INTO public.team (id, department_id, label) VALUES (12, 5, 'HR Management');
INSERT INTO public.team (id, department_id, label) VALUES (13, 6, 'Accounting');
INSERT INTO public.team (id, department_id, label) VALUES (14, 6, 'Finance');
INSERT INTO public.team (id, department_id, label) VALUES (15, 7, 'Prospection');
INSERT INTO public.team (id, department_id, label) VALUES (16, 7, 'KAM');
INSERT INTO public.team (id, department_id, label) VALUES (17, 8, 'CS Web');
INSERT INTO public.team (id, department_id, label) VALUES (18, 8, 'CS Phone');

INSERT INTO public.team (id, department_id, label) VALUES (19, 9, 'Maintenance');
INSERT INTO public.team (id, department_id, label) VALUES (20, 9, 'Development');

INSERT INTO public.security_user (id, image, is_account_non_expired, is_credentials_non_expired, is_enabled, is_non_locked, password, username, group_id, team_id) VALUES (1, 'https://actiformpro.fr/wp-content/uploads/2021/04/avatar-512.png', true, true, true, true, '$2a$10$HNgNP5stjFo2SJnZqAUfju6KEBe9DOgSEWlI6k81awXcvsm5FV3TS', 'admin', 2, 2);
INSERT INTO public.security_user (id, image, is_account_non_expired, is_credentials_non_expired, is_enabled, is_non_locked, password, username, group_id, team_id) VALUES (2, 'https://audit-controle-interne.com/wp-content/uploads/2019/03/avatar-user-teacher-312a499a08079a12-512x512.png', true, true, true, true, '$2a$10$wuu7MzAgIVuGwV.NqZPAjeDun3hHzTmBuqKVgcUMtv/l.oki/H2bW', 'user', 1, 5);

INSERT INTO public.topic (id, description, image, name, creator_id, is_active, created_at) VALUES (2, 'Chilling together with drinks!', 'https://image.shutterstock.com/image-vector/beer-glasses-mug-hand-drawn-260nw-1387979843.jpg', 'Drinks', 2, true, '2021-10-20');
INSERT INTO public.topic (id, description, image, name, creator_id, is_active, created_at) VALUES (1, 'Everyone who enjoys good music is welcome to join!', 'https://img.huffingtonpost.com/asset/5f1a00b8270000b10fe674d7.jpeg?cache=vM&ops=1200_630', 'Concerts', 1, true, '2021-10-20');
INSERT INTO public.topic (id, description, image, name, creator_id, is_active, created_at) VALUES (3, 'To anyone who likes watching a good movie', 'https://img-19.ccm2.net/kyn2_bCQILiKVW1jFOx5hVaFGNo=/480x335/smart/6246fc94bad94dda9c611a1fdc87dd76/ccmcms-commentcamarche/23887428.jpg', 'Cinema', 1, true, '2021-10-20');
INSERT INTO public.topic_subscribed_users (topic_id, subscribed_users_id) VALUES (1, 1);
INSERT INTO public.topic_subscribed_users (topic_id, subscribed_users_id) VALUES (2, 1);

INSERT INTO public.event (id, date, description, image, limited_to_department, limited_to_team, name, address_id, creator_id, topic_id, is_active, created_at) VALUES (1, '2021-10-26', 'Great outdoors concert!', 'https://p0.storage.canalblog.com/09/46/849913/111241547_o.jpg', false, false, 'Caravan Palace', 1, 1, 1, true, '2021-10-20');
INSERT INTO public.event (id, date, description, image, limited_to_department, limited_to_team, name, address_id, creator_id, topic_id, is_active, created_at) VALUES (2, '2021-10-26', 'Some drinks after work, first round free!', 'https://www.mycuisine.com/wp-content/uploads/2020/03/cocktail-Le-Cosmo-par-Se%CC%81verine-Ferrer-sur-My-Cuisine-TV.jpg', false, false, 'Beers at the Pacific', 3, 2, 2, true, '2021-10-20');
INSERT INTO public.event (id, date, description, image, limited_to_department, limited_to_team, name, address_id, creator_id, topic_id, is_active, created_at) VALUES (3, '2021-10-20', 'Let''s meet up for a hopefully good movie !', 'https://gentologie.com/wp-content/uploads/2019/12/No-Time-to-Die-Logo-Bande-Annonce.jpg', false, false, 'No Time To Die', 2, 2, 3, true, '2021-10-20');

INSERT INTO public.topic_subscribed_users (topic_id, subscribed_users_id) VALUES (3, 1);
INSERT INTO public.topic_subscribed_users (topic_id, subscribed_users_id) VALUES (2, 2);
INSERT INTO public.topic_subscribed_users (topic_id, subscribed_users_id) VALUES (2, 2);

INSERT INTO public.event_participants (event_id, participants_id) VALUES (1, 1);
INSERT INTO public.event_participants (event_id, participants_id) VALUES (2, 1);
INSERT INTO public.event_participants (event_id, participants_id) VALUES (3, 1);
INSERT INTO public.event_participants (event_id, participants_id) VALUES (2, 2);
INSERT INTO public.event_participants (event_id, participants_id) VALUES (1, 2);

INSERT INTO public.invitation (id, message, response_message, status, event_id, recipient_id, sender_id, is_active, created_at) VALUES (3, 'Hey let''s go drink!', null, 3, 2, 1, 2, true, '2021-10-20');
INSERT INTO public.invitation (id, message, response_message, status, event_id, recipient_id, sender_id, is_active, created_at) VALUES (4, 'Hey let''s go dance!', null, 3, 1, 2, 1, true, '2021-10-20');

INSERT INTO public.alert (dtype, id, category, is_handled, message, response_message, event_id, topic_id, creator_id, is_active, created_at) VALUES ('AlertEvent', 1, 1, false, 'Really bad movie', null, 3, null, 1, true, '2021-10-20');
INSERT INTO public.alert (dtype, id, category, is_handled, message, response_message, event_id, topic_id, creator_id, is_active, created_at) VALUES ('AlertEvent', 2, 0, false, 'invites to drink a lot', null, 2, null, 2, true, '2021-10-20');
