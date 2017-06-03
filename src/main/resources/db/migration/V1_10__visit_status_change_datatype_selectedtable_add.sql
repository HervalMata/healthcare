ALTER TABLE `health_care_v1_dev`.`visit` MODIFY `status` VARCHAR(48) NOT NULL;
ALTER TABLE `health_care_v1_dev`.`visit` ADD COLUMN `selected_table` VARCHAR(255) NULL;
