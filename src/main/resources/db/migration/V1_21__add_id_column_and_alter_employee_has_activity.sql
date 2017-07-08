ALTER TABLE ${db}.`employee_has_activity` DROP PRIMARY KEY;
ALTER TABLE ${db}.`employee_has_activity` ADD COLUMN `id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT;
