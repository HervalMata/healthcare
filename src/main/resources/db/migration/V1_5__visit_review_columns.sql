ALTER TABLE ${db}.`visit` DROP FOREIGN KEY `fk_visit_signature_type1` ; 
ALTER TABLE ${db}.`visit` DROP INDEX `fk_visit_signature_type1_idx` ; 
ALTER TABLE ${db}.`visit` DROP COLUMN `signature_type_id`;