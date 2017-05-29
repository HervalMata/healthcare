CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`serviceplan` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `approvedby` VARCHAR(255) NOT NULL COMMENT 'No matter this plan is proved by one employee or not, the name of the person should be recorded here. ',
  `employee_id` INT NULL DEFAULT NULL COMMENT 'If this plan is proved by one employee, then this employee’s ID will be recorded here.',
  `plan_start` DATETIME NULL DEFAULT NULL,
  `plan_end` DATETIME NULL DEFAULT NULL,
  `days` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Record days in week for services.',
  `docurl` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_serviceplan_user1_idx` (`user_id`),
  CONSTRAINT `fk_serviceplan_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care_v1_dev`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;