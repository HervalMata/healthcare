ALTER TABLE ${db}.`training_has_employee` DROP PRIMARY KEY;
ALTER TABLE ${db}.`training_has_employee` ADD COLUMN `id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT;
