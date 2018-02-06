/*
|----------------------------------------------------------------------------
| CREATE 'users' TABLE
|----------------------------------------------------------------------------
*/
CREATE TABLE `user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(32) NOT NULL,
  `last_name` VARCHAR(32) NOT NULL,
  `age` INT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`));

/*
|----------------------------------------------------------------------------
| CREATE 'posts' TABLE
|----------------------------------------------------------------------------
*/
CREATE TABLE `post` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(128) NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `published_at` DATETIME NULL DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`));

/*
|----------------------------------------------------------------------------
| CREATE FOREIGN KEY
|----------------------------------------------------------------------------
*/
ALTER TABLE `post`
ADD INDEX `fk_user_id_key_idx` (`user_id` ASC);
ALTER TABLE `post`
ADD CONSTRAINT `fk_user_id_key`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;