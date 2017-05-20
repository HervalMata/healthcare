-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`visit`
-- -----------------------------------------------------
ALTER TABLE `health_care_v1_dev`.`visit`
    MODIFY `meal_id` INT UNSIGNED DEFAULT NULL;

ALTER TABLE `health_care_v1_dev`.`visit`
    MODIFY `selected_activity_id` INT UNSIGNED DEFAULT NULL;

ALTER TABLE `health_care_v1_dev`.`visit`
    MODIFY `selected_meal_id` INT UNSIGNED DEFAULT NULL;

ALTER TABLE `health_care_v1_dev`.`meal`
    CHANGE COLUMN `verified_by_ nutritionist` `verified_by_nutritionist` INT NULL DEFAULT '0';