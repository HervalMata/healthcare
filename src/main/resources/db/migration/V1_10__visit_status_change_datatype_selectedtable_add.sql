ALTER TABLE ${db}.`visit` MODIFY `status` VARCHAR(48) NOT NULL;
ALTER TABLE ${db}.`visit` ADD COLUMN `selected_table` VARCHAR(255) NULL;
