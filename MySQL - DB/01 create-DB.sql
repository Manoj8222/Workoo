CREATE DATABASE IF NOT EXISTS `workoodatabase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `workoodatabase`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(15) DEFAULT NULL,
  `password` VARCHAR(20) NOT NULL,
  `img` MEDIUMBLOB DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `tasker`;
CREATE TABLE `tasker` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tasker_name` VARCHAR(45) DEFAULT NULL,
  `password` VARCHAR(20) DEFAULT NULL,
  `img` MEDIUMBLOB DEFAULT NULL,
  `skill` VARCHAR(100) DEFAULT NULL,
  `description` TEXT DEFAULT NULL,
  `fair` INT(11) DEFAULT NULL,
  `location` VARCHAR(30) DEFAULT NULL,
  `rating` DECIMAL(3,2) DEFAULT NULL,
  `review` TEXT DEFAULT NULL,
  `total_project` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL,
  `date` DATETIME DEFAULT NULL,
  `time` TIME DEFAULT NULL,
  `tasker_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`tasker_id`) REFERENCES `tasker`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL,
  `date` DATETIME DEFAULT NULL,
  `rating` DECIMAL(3,2) DEFAULT NULL,
  `tasker_id` BIGINT(20) DEFAULT NULL,
  `review_description` TEXT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`tasker_id`) REFERENCES `tasker`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `booking_history`;
CREATE TABLE `booking_history` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL,
  `tasker_id` BIGINT(20) DEFAULT NULL,
  `booking_date` VARCHAR(45) DEFAULT NULL,
  `booking_time` VARCHAR(45) DEFAULT NULL,
  `img` MEDIUMBLOB DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`tasker_id`) REFERENCES `tasker`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `tasker_history`;
CREATE TABLE `tasker_history` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL,
  `tasker_id` BIGINT(20) DEFAULT NULL,
  `booking_date` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`tasker_id`) REFERENCES `tasker`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
