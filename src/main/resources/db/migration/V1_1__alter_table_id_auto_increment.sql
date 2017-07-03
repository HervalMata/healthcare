-- -----------------------------------------------------
-- Alter Table ${db}.`role`
-- -----------------------------------------------------
ALTER TABLE ${db}.`admin`
    DROP FOREIGN KEY `fk_admin_role1`,
    MODIFY `role_id` INT UNSIGNED NOT NULL;
    
ALTER TABLE ${db}.`menu`
    DROP FOREIGN KEY `fk_menu_role1`,
    MODIFY `role_id` INT UNSIGNED NOT NULL;

ALTER TABLE ${db}.`report`
    DROP FOREIGN KEY `fk_report_role1`,
    MODIFY `role_id` INT UNSIGNED NOT NULL;
    
ALTER TABLE ${db}.`role` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE ${db}.`admin`
    ADD CONSTRAINT `fk_admin_role1` FOREIGN KEY (`role_id`)
    REFERENCES ${db}.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE ${db}.`menu`
    ADD CONSTRAINT `fk_menu_role1` FOREIGN KEY (`role_id`)
    REFERENCES ${db}.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE ${db}.`report`
    ADD CONSTRAINT `fk_report_role1` FOREIGN KEY (`role_id`)
    REFERENCES ${db}.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

-- -----------------------------------------------------
-- Alter Table ${db}.`admin_post`
-- -----------------------------------------------------
ALTER TABLE ${db}.`admin_post` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;

-- -----------------------------------------------------
-- Alter Table ${db}.`menu`
-- -----------------------------------------------------
ALTER TABLE ${db}.`menu` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;

-- -----------------------------------------------------
-- Alter Table ${db}.`report`
-- -----------------------------------------------------
ALTER TABLE ${db}.`report` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;