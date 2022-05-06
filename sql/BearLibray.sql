/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.27-0ubuntu0.20.04.1 : Database - bear
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `bear`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `valid` INT NOT NULL DEFAULT '1',
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  `create_id` BIGINT DEFAULT NULL,
  `create_name` VARCHAR(50) DEFAULT NULL,
  `modified_id` BIGINT DEFAULT NULL,
  `modified_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*Table structure for table `check` */

DROP TABLE IF EXISTS `check`;

CREATE TABLE `check` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT DEFAULT NULL,
  `administrator_id` BIGINT DEFAULT NULL,
  `type` INT DEFAULT NULL COMMENT '类别（0：行走；1：阅读；2：体育；3：艺术；4：实践；5：学习进步目标）',
  `url` VARCHAR(200) DEFAULT NULL,
  `point` BIGINT DEFAULT NULL,
  `exp` BIGINT DEFAULT NULL,
  `comment` VARCHAR(200) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  `create_id` BIGINT DEFAULT NULL,
  `create_name` VARCHAR(50) DEFAULT NULL,
  `modified_id` BIGINT DEFAULT NULL,
  `modified_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*Table structure for table `contribution` */

DROP TABLE IF EXISTS `contribution`;

CREATE TABLE `contribution` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `administrator_id` BIGINT DEFAULT NULL,
  `user_id` BIGINT DEFAULT NULL,
  `point` BIGINT DEFAULT NULL,
  `comment` VARCHAR(500) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  `create_id` BIGINT DEFAULT NULL,
  `create_name` VARCHAR(50) DEFAULT NULL,
  `modified_id` BIGINT DEFAULT NULL,
  `modified_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*Table structure for table `invitation_code` */

DROP TABLE IF EXISTS `invitation_code`;

CREATE TABLE `invitation_code` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `administrator_id` BIGINT DEFAULT NULL,
  `user_id` BIGINT DEFAULT NULL,
  `code` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `valid_time` DATETIME DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  `create_id` BIGINT DEFAULT NULL,
  `create_name` VARCHAR(50) DEFAULT NULL,
  `modified_id` BIGINT DEFAULT NULL,
  `modified_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT DEFAULT NULL,
  `text` VARCHAR(200) DEFAULT NULL,
  `link` VARCHAR(200) DEFAULT NULL,
  `read` INT DEFAULT '0' COMMENT '是否已读',
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  `create_id` BIGINT DEFAULT NULL,
  `create_name` VARCHAR(50) DEFAULT NULL,
  `modified_id` BIGINT DEFAULT NULL,
  `modified_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT DEFAULT NULL,
  `url` VARCHAR(200) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modified` DATETIME DEFAULT NULL,
  `create_id` BIGINT DEFAULT NULL,
  `create_name` VARCHAR(50) DEFAULT NULL,
  `modified_id` BIGINT DEFAULT NULL,
  `modified_name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `invitation_code_id` BIGINT DEFAULT NULL,
  `nickname` VARCHAR(50) DEFAULT NULL,
  `avatar` VARCHAR(200) DEFAULT NULL,
  `birthday` VARCHAR(50) DEFAULT NULL,
  `gender` INT DEFAULT '2',
  `phone` VARCHAR(50) DEFAULT NULL,
  `honor_point` BIGINT DEFAULT NULL COMMENT '荣誉值',
  `self_control_point` BIGINT DEFAULT NULL COMMENT '自制力',
  `contribution_point` BIGINT DEFAULT NULL COMMENT '贡献值',
  `walk` BIGINT DEFAULT NULL,
  `read` BIGINT DEFAULT NULL,
  `sport` BIGINT DEFAULT NULL,
  `art` BIGINT DEFAULT NULL,
  `practice` BIGINT DEFAULT NULL,
  `valid` int DEFAULT '1',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `create_id` bigint DEFAULT NULL,
  `create_name` varchar(50) DEFAULT NULL,
  `modified_id` bigint DEFAULT NULL,
  `modified_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
