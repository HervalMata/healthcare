CREATE TABLE IF NOT EXISTS `health_care_v1_dev`.`work_item` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(255) NOT NULL,
  `item_note` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = ' help on cooking, help on shopping, help on laundry, etc.';

ALTER TABLE `health_care_v1_dev`.`home_visit` DROP FOREIGN KEY `fk_home_visit_meal` ; 
ALTER TABLE `health_care_v1_dev`.`home_visit` DROP INDEX `idx_home_visit_meal` ; 
ALTER TABLE `health_care_v1_dev`.`home_visit` DROP COLUMN `meal_id`;

ALTER TABLE `health_care_v1_dev`.`home_visit` ADD COLUMN `worked_items` VARCHAR(255) NULL;