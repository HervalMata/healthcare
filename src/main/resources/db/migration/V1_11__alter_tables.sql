ALTER TABLE ${db}.`visit`
    MODIFY `selected_meal_id` INT UNSIGNED DEFAULT NULL;

ALTER TABLE ${db}.`meal`
    CHANGE COLUMN `verified_by_ nutritionist` `verified_by_nutritionist` INT NULL DEFAULT '0';