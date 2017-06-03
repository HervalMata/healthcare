ALTER TABLE `health_care_v1_dev`.`visit` DROP COLUMN `meal_id`;
ALTER TABLE `health_care_v1_dev`.`visit` DROP COLUMN `selected_activity_id`;
ALTER TABLE `health_care_v1_dev`.`visit` MODIFY `selected_meal_id` INT UNSIGNED NULL;
ALTER TABLE `health_care_v1_dev`.`visit` MODIFY `selected_seat` VARCHAR(255) NULL;