CREATE TABLE `file_upload` (
  `id` int(10) unsigned NOT NULL,
  `entity` varchar(255) NOT NULL COMMENT 'a entity name that such document belong',
  `entity_id` int(10) unsigned NOT NULL COMMENT 'an id id row entity that such document belong',
  `file_class` varchar(255) NOT NULL COMMENT 'a unique name for a type document ej MEDICALCERTIFICATION',
  `file_name` varchar(500) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

