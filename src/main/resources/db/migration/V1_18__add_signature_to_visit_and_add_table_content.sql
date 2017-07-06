ALTER TABLE ${db}.`visit` ADD COLUMN `signature` VARCHAR(255) NULL;

CREATE TABLE ${db}.`content` (  
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,  
  `parent_id` INT(10) UNSIGNED NULL DEFAULT NULL,  
  `title` varchar(255) NOT NULL,  
  `content` longblob NOT NULL,  
  `page_title` varchar(255) NOT NULL,  
  `page_keyword` text NOT NULL,  
  `page_description` text NOT NULL,  
  `accessKey` varchar(255) DEFAULT NULL,  
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,  
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,  
  `status` tinyint(4) NOT NULL,  
  `base_id` int(11) NOT NULL,  
  PRIMARY KEY (`id`),  
  UNIQUE KEY `fld_accessNo` (`accessKey`)) 
ENGINE=InnoDB AUTO_INCREMENT=107 
DEFAULT CHARSET=utf8;

ALTER TABLE ${db}.`user` ADD COLUMN `status_second` tinyint(4) NULL;

