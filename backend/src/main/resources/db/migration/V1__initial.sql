CREATE TABLE IF NOT EXISTS `boodschappendb`.`lijst` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `done` TINYINT(1) NULL,
  `item` TEXT NULL,
  `winkel` TEXT NULL,
  PRIMARY KEY (`id`))