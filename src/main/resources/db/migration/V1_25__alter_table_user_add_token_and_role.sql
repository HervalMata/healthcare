-- -----------------------------------------------------
-- Alter Table ${db}.`visit_has_activity`
-- -----------------------------------------------------
ALTER TABLE  ${db}.`user` 
	ADD COLUMN `remember_token` VARCHAR(255) NULL DEFAULT NULL,
	ADD COLUMN `role_id` INT UNSIGNED NULL,
	ADD INDEX `fk_admin_role1_idx` (`role_id`),
  ADD CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES ${db}.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT; 	
