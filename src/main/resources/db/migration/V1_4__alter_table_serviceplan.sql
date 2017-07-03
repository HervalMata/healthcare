-- -----------------------------------------------------
-- Alter Table ${db}.`serviceplan`
-- -----------------------------------------------------
ALTER TABLE ${db}.`serviceplan`
    MODIFY `employee_id` INT UNSIGNED NULL DEFAULT NULL,
    ADD INDEX `fk_serviceplan_employee_idx` (`employee_id`),
	ADD CONSTRAINT `fk_serviceplan_employee` FOREIGN KEY (`employee_id`)
    REFERENCES ${db}.`employee` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;