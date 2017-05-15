-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`role`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`admin`
    DROP FOREIGN KEY `fk_admin_role1`,
    MODIFY `role_id` INT UNSIGNED NOT NULL;
    
ALTER TABLE `health_care_v1_dev`.`menu`
    DROP FOREIGN KEY `fk_menu_role1`,
    MODIFY `role_id` INT UNSIGNED NOT NULL;

ALTER TABLE `health_care_v1_dev`.`report`
    DROP FOREIGN KEY `fk_report_role1`,
    MODIFY `role_id` INT UNSIGNED NOT NULL;
    
ALTER TABLE `health_care_v1_dev`.`role` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE `health_care_v1_dev`.`admin`
    ADD CONSTRAINT `fk_admin_role1` FOREIGN KEY (`role_id`)
    REFERENCES `health_care_v1_dev`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `health_care_v1_dev`.`menu`
    ADD CONSTRAINT `fk_menu_role1` FOREIGN KEY (`role_id`)
    REFERENCES `health_care_v1_dev`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE `health_care_v1_dev`.`report`
    ADD CONSTRAINT `fk_report_role1` FOREIGN KEY (`role_id`)
    REFERENCES `health_care_v1_dev`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`admin_post`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`admin_post` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;

-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`menu`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`menu` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;

-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`report`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`report` 
	MODIFY `id` INT UNSIGNED NOT NULL AUTO_INCREMENT;