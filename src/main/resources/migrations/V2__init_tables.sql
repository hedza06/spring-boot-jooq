/*
|---------------------------------------------------------------------------------------
| USERS TABLE SEED
|---------------------------------------------------------------------------------------
*/
INSERT INTO `user` (`first_name`, `last_name`, `age`)
VALUES
  ('Heril', 'Muratovic', 26),
  ('Marko', 'Andjelic', 26),
  ('Boris', 'Tuponja', 26),
  ('Mirko', 'Markovic', 26),
  ('Marko', 'Vujovic', 26);

/*
|---------------------------------------------------------------------------------------
| POSTS TABLE SEED
|---------------------------------------------------------------------------------------
*/
INSERT INTO `post` (`title`, `user_id`, `published_at`)
VALUES
  ('Architecting Spring Boot App', (SELECT id FROM `user` WHERE first_name = 'Heril'), '2018-02-25 00:00:00'),
  ('Bootstrapping Angular App', (SELECT id FROM `user` WHERE last_name = 'Andjelic'), '2018-02-24 00:00:00'),
  ('Hibernate Performance Boost', (SELECT id FROM `user` WHERE first_name = 'Heril'), '2018-02-23 00:00:00'),
  ('Spring Boot and JOOQ', (SELECT id FROM `user` WHERE first_name = 'Boris'), '2018-02-22 00:00:00'),
  ('Using Flyway for database migrations and seeds', (SELECT id FROM `user` WHERE first_name = 'Mirko'), '2018-02-21 00:00:00'),
  ('Angular with RxJs', (SELECT id FROM `user` WHERE last_name = 'Vujovic'), '2018-02-21 00:00:00');