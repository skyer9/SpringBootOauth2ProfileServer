
CREATE TABLE `tbl_user` (
    `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `email` varchar(255) NOT NULL,
    `password` VARCHAR(100) NULL DEFAULT NULL,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    `roles` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `UK_tbl_user_email` (`email`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
