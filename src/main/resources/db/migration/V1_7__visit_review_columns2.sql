ALTER TABLE ${db}.`visit` DROP COLUMN `meal_id`;
ALTER TABLE ${db}.`visit` DROP COLUMN `selected_activity_id`;
ALTER TABLE ${db}.`visit` MODIFY `selected_meal_id` INT UNSIGNED NULL;
ALTER TABLE ${db}.`visit` MODIFY `selected_seat` VARCHAR(255) NULL;