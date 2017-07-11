-- -----------------------------------------------------
-- Alter Table `health_care_v1_dev`.`serviceplan`
-- -----------------------------------------------------
ALTER TABLE ${db}.`serviceplan` 
    DROP PRIMARY KEY, ADD PRIMARY KEY (`id`);