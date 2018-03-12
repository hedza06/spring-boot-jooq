DROP procedure IF EXISTS `post_proc`;

DELIMITER $$
CREATE PROCEDURE `post_procedure` (IN title VARCHAR(64), OUT post_count INT)
BEGIN
	SELECT COUNT(*) INTO post_count
    FROM post
    WHERE post.title = title;
END$$
DELIMITER ;