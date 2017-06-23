CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`caregiver` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `agency_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `caregiver_type` INT(11) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `middle_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `language` VARCHAR(255) NULL DEFAULT NULL,
  `social_security_number` VARCHAR(255) NULL DEFAULT NULL,
  `dob` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `secondary_phone` VARCHAR(255) NULL DEFAULT NULL,
  `verification_code` VARCHAR(255) NULL DEFAULT NULL,
  `address_type` VARCHAR(255) NULL DEFAULT NULL,
  `address_one` VARCHAR(255) NULL DEFAULT NULL,
  `address_two` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `state` VARCHAR(255) NULL DEFAULT NULL,
  `zipcode` VARCHAR(255) NULL DEFAULT NULL,
  `profile_photo` VARCHAR(255) NULL DEFAULT NULL,
  `certificate` VARCHAR(255) NOT NULL,
  `certificate_start` DATETIME NOT NULL,
  `certificate_end` DATETIME NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `vacation_note` VARCHAR(1000) NULL DEFAULT NULL,
  `vacation_start` DATETIME NULL DEFAULT NULL,
  `vacation_end` DATETIME NULL DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_caregiver_agency` (`agency_id`),
  INDEX `idx_caregiver_company` (`company_id`),
  CONSTRAINT `fk_caregiver_company`
    FOREIGN KEY (`company_id`)
    REFERENCES `health_care_v1_dev`.`company` (`id`),
  CONSTRAINT `fk_caregiver_agency`
    FOREIGN KEY (`agency_id`)
    REFERENCES `health_care_v1_dev`.`agency` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Table storing all customers. Holds foreign keys to the address table and the store table where this customer is registered.\n\nBasic information about the customer like first and last name are stored in the table itself. Same for the date the record was created and when the information was last updated.';



CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`home_visit` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `check_in_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `meal_id` INT(10) UNSIGNED NULL,
  `carereceiver_signature` VARCHAR(255) NULL DEFAULT NULL,
  `check_out_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `carereceiver_comments` VARCHAR(1000) NULL DEFAULT NULL,
  `notes` VARCHAR(1000) NULL DEFAULT NULL,
  `status` VARCHAR(48) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `serviceplan_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `caregiver_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_home_visit_meal` (`meal_id`),
  INDEX `idx_home_visit_serviceplan` (`serviceplan_id`),
  INDEX `idx_home_visit_user` (`user_id`),
  INDEX `idx_home_visit_caregiver` (`caregiver_id`),
  CONSTRAINT `fk_home_visit_serviceplan`
    FOREIGN KEY (`serviceplan_id`)
    REFERENCES `health_care_v1_dev`.`serviceplan` (`id`),
  CONSTRAINT `fk_home_visit_meal`
    FOREIGN KEY (`meal_id`)
    REFERENCES `health_care_v1_dev`.`meal` (`id`),
  CONSTRAINT `fk_home_visit_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `health_care_v1_dev`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_home_visit_caregiver`
    FOREIGN KEY (`caregiver_id`)
    REFERENCES `health_care_v1_dev`.`caregiver` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;