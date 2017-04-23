-- -----------------------------------------------------
-- Table `admin_permission`
-- -----------------------------------------------------
CREATE  TABLE `admin_permission` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `level` INT NULL ,
  `level_name` VARCHAR(45) NULL ,
  `status` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- Placeholder
INSERT INTO admin_permission (level, level_name, status) VALUES (1, "admin", 1);