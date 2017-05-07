-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`company` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `license_no` VARCHAR(255) NOT NULL,
  `federal_tax` VARCHAR(255) NOT NULL,
  `federal_tax_start` DATETIME NOT NULL,
  `federal_tax_expire` DATETIME NOT NULL,
  `federal_tax_status` INT NOT NULL,
  `state_tax` VARCHAR(255) NOT NULL,
  `state_tax_start` DATETIME NOT NULL,
  `state_tax_expire` DATETIME NOT NULL,
  `state_tax_status` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `fax` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `address_one` VARCHAR(255) NOT NULL,
  `address_two` VARCHAR(255) NOT NULL,
  `worktime_start` TIME NOT NULL DEFAULT '00:00:00',
  `worktime_end` TIME NOT NULL DEFAULT '23:59:59',
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `zipcode` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` INT NOT NULL,
  `days_work` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Mon, Tue, Wed, Thu, Fri, Sat, Sun.',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`agency_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`agency_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `status` INT NOT NULL COMMENT 'type status',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`agency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`agency` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `license_no` VARCHAR(255) NOT NULL,
  `company_id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `tracking_mode` INT NOT NULL,
  `contact_person` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `address_one` VARCHAR(255) NOT NULL,
  `address_two` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `zipcode` VARCHAR(255) NOT NULL,
  `timezone` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `holiday` VARCHAR(1000) NOT NULL,
  `fax` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `company_id1` INT UNSIGNED NOT NULL,
  `agency_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_agency_company1_idx` (`company_id1`),
  INDEX `fk_agency_agency_type1_idx` (`agency_type_id`),
  CONSTRAINT `fk_agency_company1`
    FOREIGN KEY (`company_id1`)
    REFERENCES `health_care_v1_dev`.`company` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_agency_agency_type1`
    FOREIGN KEY (`agency_type_id`)
    REFERENCES `health_care_v1_dev`.`agency_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` INT UNSIGNED NULL DEFAULT NULL,
  `agency_id` INT UNSIGNED NULL DEFAULT NULL,
  `user_type` INT NOT NULL,
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
  `emergency_contact_first_name` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_middle_name` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_last_name` VARCHAR(255) NULL DEFAULT NULL,
  `relationship_to_paticipant` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_phone` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_address_one` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_address_two` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_city` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_state` VARCHAR(255) NULL DEFAULT NULL,
  `emergency_contact_zipcode` VARCHAR(255) NULL DEFAULT NULL,
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  `preferred_meal_id` INT UNSIGNED NULL DEFAULT NULL,
  `preferred_activity_id` INT UNSIGNED NULL DEFAULT NULL,
  `preferred_seat` VARCHAR(255) NULL DEFAULT NULL,
  `profile_photo` VARCHAR(255) NULL DEFAULT NULL,
  `approvable_mail` INT NULL DEFAULT NULL,
  `medicaid_no` VARCHAR(255) NOT NULL,
  `medicare_no` VARCHAR(255) NULL DEFAULT NULL,
  `insurance` VARCHAR(255) NULL DEFAULT NULL,
  `insurance_start` DATETIME NOT NULL,
  `insurance_end` DATETIME NOT NULL,
  `insurance_eligiable` VARCHAR(255) NOT NULL,
  `eligiable_start` DATETIME NOT NULL,
  `eligiable_end` DATETIME NOT NULL,
  `family_doctor` VARCHAR(255) NOT NULL,
  `family_doctor_address` VARCHAR(255) NULL DEFAULT NULL,
  `family_doctor_tel` VARCHAR(255) NULL DEFAULT NULL,
  `expert_doctor` VARCHAR(255) NULL DEFAULT NULL,
  `expert_doctor_address` VARCHAR(255) NULL DEFAULT NULL,
  `expert_doctor_tel` VARCHAR(255) NULL DEFAULT NULL,
  `medical_condition` VARCHAR(1000) NULL DEFAULT NULL,
  `status` INT NULL DEFAULT NULL,
  `vacation_note` VARCHAR(1000) NULL,
  `vacation_start` DATETIME NULL DEFAULT NULL,
  `vacation_end` DATETIME NULL DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_last_name` (`agency_id`),
  INDEX `fk_user_agency1_idx` (`agency_id`),
  CONSTRAINT `fk_user_agency1`
    FOREIGN KEY (`agency_id`)
    REFERENCES `health_care_v1_dev`.`agency` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Table storing all customers. Holds foreign keys to the address table and the store table where this customer is registered.\n\nBasic information about the customer like first and last name are stored in the table itself. Same for the date the record was created and when the information was last updated.';


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`signature_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`signature_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`visit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`visit` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `viti_agency_id` INT UNSIGNED NOT NULL,
  `check_in_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `selected_meal_id` INT UNSIGNED NOT NULL,
  `selected_activity_id` INT UNSIGNED NOT NULL,
  `selected_seat` VARCHAR(255) NULL DEFAULT NULL,
  `user_signature` VARCHAR(255) NULL DEFAULT NULL,
  `user_signature_type` INT UNSIGNED NOT NULL,
  `user_barcode_id` VARCHAR(255) NULL DEFAULT NULL,
  `check_out_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_comments` VARCHAR(1000) NULL DEFAULT NULL,
  `notes` VARCHAR(1000) NULL DEFAULT NULL,
  `status` INT NULL DEFAULT '0',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id1` INT UNSIGNED NOT NULL,
  `user_id2` INT UNSIGNED NOT NULL,
  `signature_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `user_id2`, `signature_type_id`),
  INDEX `fk_visit_user1_idx` (`user_id2`),
  INDEX `fk_visit_signature_type1_idx` (`signature_type_id`),
  CONSTRAINT `fk_visit_user1`
    FOREIGN KEY (`user_id2`)
    REFERENCES `health_care_v1_dev`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_visit_signature_type1`
    FOREIGN KEY (`signature_type_id`)
    REFERENCES `health_care_v1_dev`.`signature_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`role` (
  `id` INT UNSIGNED NOT NULL,
  `level` INT NOT NULL,
  `level_name` VARCHAR(255) NOT NULL,
  `status` INT NOT NULL,
  `agency_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `level_UNIQUE` (`level`),
  INDEX `fk_role_agency1_idx` (`agency_id`),
  CONSTRAINT `fk_role_agency1`
    FOREIGN KEY (`agency_id`)
    REFERENCES `health_care_v1_dev`.`agency` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`admin` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `middle_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `gender` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `secondary_phone` VARCHAR(255) NULL DEFAULT NULL,
  `profile_photo` VARCHAR(255) NULL DEFAULT NULL,
  `ip` VARCHAR(255) NULL DEFAULT NULL,
  `device_address` VARCHAR(255) NULL DEFAULT NULL,
  `remember_token` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `status` INT NOT NULL,
  `role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_admin_role1_idx` (`role_id`),
  CONSTRAINT `fk_admin_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `health_care_v1_dev`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`admin_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`admin_post` (
  `id` INT UNSIGNED NOT NULL,
  `post_text` VARCHAR(1000) NOT NULL,
  `post_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `status` INT NOT NULL,
  `admin_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_admin_post_admin1_idx` (`admin_id`),
  CONSTRAINT `fk_admin_post_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `health_care_v1_dev`.`admin` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`menu` (
  `id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `angular_url` VARCHAR(255) NOT NULL,
  `page` VARCHAR(255) NOT NULL,
  `class` VARCHAR(255) NOT NULL,
  `img_url` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `display_order` INT NULL DEFAULT '0',
  `status` INT NULL DEFAULT '1',
  `role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_menu_role1_idx` (`role_id`),
  CONSTRAINT `fk_menu_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `health_care_v1_dev`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`meal` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `meal_class` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `ingredients` VARCHAR(1000) NULL DEFAULT NULL,
  `notes` VARCHAR(1000) NULL DEFAULT NULL,
  `verified_by_ nutritionist` INT NULL DEFAULT '0',
  `status` INT NULL DEFAULT '0',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `visit_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_visit1_idx` (`visit_id`),
  CONSTRAINT `fk_meal_visit1`
    FOREIGN KEY (`visit_id`)
    REFERENCES `health_care_v1_dev`.`visit` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`activity` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `status` INT NULL DEFAULT '0',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `instructor_employee_id` INT UNSIGNED NOT NULL,
  `time_start` VARCHAR(255) NULL DEFAULT NULL,
  `time_end` VARCHAR(255) NULL DEFAULT NULL,
  `date` VARCHAR(255) NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `note` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`report` (
  `id` INT UNSIGNED NOT NULL,
  `base_id` INT UNSIGNED NOT NULL,
  `company_id` INT UNSIGNED NOT NULL,
  `admin_id` INT UNSIGNED NOT NULL,
  `report_title` VARCHAR(255) NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `data_columns` VARCHAR(255) NOT NULL,
  `format` VARCHAR(255) NULL DEFAULT NULL,
  `download_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_report_role1_idx` (`role_id`),
  CONSTRAINT `fk_report_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `health_care_v1_dev`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`visit_has_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`visit_has_activity` (
  `visit_id` INT UNSIGNED NOT NULL,
  `activity_id` INT UNSIGNED NOT NULL,
  `table` VARCHAR(255) NULL,
  `seat` VARCHAR(255) NULL,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  INDEX `fk_visit_has_activity_activity1_idx` (`activity_id`),
  INDEX `fk_visit_has_activity_visit1_idx` (`visit_id`),
  CONSTRAINT `fk_visit_has_activity_visit1`
    FOREIGN KEY (`visit_id`)
    REFERENCES `health_care_v1_dev`.`visit` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_visit_has_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `health_care_v1_dev`.`activity` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`review` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `employee_id` INT UNSIGNED NOT NULL,
  `affect_start` DATETIME NULL DEFAULT NULL,
  `affect_end` DATETIME NULL DEFAULT NULL,
  `content` INT NULL DEFAULT '0',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id1` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_user1_idx` (`user_id1`),
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id1`)
    REFERENCES `health_care_v1_dev`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`employee` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `gender` VARCHAR(255) NOT NULL,
  `social_security_number` VARCHAR(255) NOT NULL,
  `date_of_birth` DATETIME NOT NULL,
  `physical_exam` VARCHAR(255) NOT NULL,
  `certificate_name` VARCHAR(255) NULL DEFAULT NULL,
  `certificate_start` DATETIME NULL DEFAULT NULL,
  `certificate_end` DATETIME NULL DEFAULT NULL,
  `work_start` DATETIME NOT NULL,
  `work_end` DATETIME NULL DEFAULT NULL,
  `position` VARCHAR(255) NULL DEFAULT NULL,
  `manager` VARCHAR(255) NOT NULL COMMENT 'could be part-time, full-time, consultant, volunteer, etc.',
  `type` VARCHAR(255) NOT NULL COMMENT 'could be part-time, full-time, consultant, volunteer, etc.',
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `background_check` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `agency_id` INT UNSIGNED NOT NULL,
  `review_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_agency1_idx` (`agency_id`),
  INDEX `fk_employee_review1_idx` (`review_id`),
  CONSTRAINT `fk_employee_agency1`
    FOREIGN KEY (`agency_id`)
    REFERENCES `health_care_v1_dev`.`agency` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_employee_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `health_care_v1_dev`.`review` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`employee_has_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`employee_has_activity` (
  `employee_id` INT UNSIGNED NOT NULL,
  `activity_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`employee_id`, `activity_id`),
  INDEX `fk_employee_has_activity_activity1_idx` (`activity_id`),
  INDEX `fk_employee_has_activity_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_employee_has_activity_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `health_care_v1_dev`.`employee` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_employee_has_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `health_care_v1_dev`.`activity` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`training`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`training` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `type` INT NOT NULL,
  `trainer` VARCHAR(255) NULL DEFAULT NULL,
  `location` VARCHAR(255) NOT NULL,
  `note` VARCHAR(1000) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `health_care_v1_dev`.`training_has_employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`training_has_employee` (
  `training_id` INT UNSIGNED NOT NULL,
  `employee_id` INT UNSIGNED NOT NULL,
  `notes` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`training_id`, `employee_id`),
  INDEX `fk_training_has_employee_employee1_idx` (`employee_id`),
  INDEX `fk_training_has_employee_training1_idx` (`training_id`),
  CONSTRAINT `fk_training_has_employee_training1`
    FOREIGN KEY (`training_id`)
    REFERENCES `health_care_v1_dev`.`training` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_training_has_employee_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `health_care_v1_dev`.`employee` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
