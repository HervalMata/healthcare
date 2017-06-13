ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `health_condition` VARCHAR(9000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `card_pulm_condition` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `activity_details` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `pain_details` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `fuctional_status_adls_iadls` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `nutrition_condition` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `communication_hearing_condition` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `psychological_social_condition` VARCHAR(1000) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `assessment_reason` VARCHAR(255) NOT NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `assessment_reason_other` VARCHAR(1500) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `assessment_source_information` VARCHAR(255) NOT NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `assessment_date` DATETIME NOT NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `discharge_date` DATETIME NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `discharge_reason` VARCHAR(1500) NULL;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `discharge_comments` VARCHAR(1500) NULL;
ALTER TABLE `health_care_v1_dev`.`review` DROP COLUMN `affect_start`;
ALTER TABLE `health_care_v1_dev`.`review` DROP COLUMN `affect_end`;
ALTER TABLE `health_care_v1_dev`.`review` DROP COLUMN `content`;
ALTER TABLE `health_care_v1_dev`.`review` DROP FOREIGN KEY `fk_review_user1` ; 
ALTER TABLE `health_care_v1_dev`.`review` DROP COLUMN `user_id1`;
ALTER TABLE `health_care_v1_dev`.`review` ADD COLUMN `state` VARCHAR(255) NOT NULL;
