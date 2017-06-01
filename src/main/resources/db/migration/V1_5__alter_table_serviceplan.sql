-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`serviceplan`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`serviceplan`
    MODIFY `employee_id` INT UNSIGNED NULL DEFAULT NULL,
    ADD INDEX `fk_serviceplan_employee_idx` (`employee_id`),
	ADD CONSTRAINT `fk_serviceplan_employee` FOREIGN KEY (`employee_id`)
    REFERENCES `health_care_v1_dev`.`employee` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`employee`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`employee`
	DROP FOREIGN KEY `fk_employee_review1`,
	DROP INDEX `fk_employee_review1_idx`,
	DROP COLUMN review_id;