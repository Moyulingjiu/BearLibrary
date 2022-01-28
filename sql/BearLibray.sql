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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bear` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bear`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `valid` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `administrator` */

/*Table structure for table `check` */

DROP TABLE IF EXISTS `check`;

CREATE TABLE `check` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `administrator_id` bigint DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '类别（0：行走；1：阅读；2：体育；3：艺术；4：实践；5：学习进步目标）',
  `url` varchar(200) DEFAULT NULL,
  `point` bigint DEFAULT NULL,
  `exp` bigint DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `check` */

/*Table structure for table `contribution` */

DROP TABLE IF EXISTS `contribution`;

CREATE TABLE `contribution` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `administrator_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `point` bigint DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `contribution` */

/*Table structure for table `invitation_code` */

DROP TABLE IF EXISTS `invitation_code`;

CREATE TABLE `invitation_code` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `administrator_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `valid_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `invitation_code` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `text` varchar(200) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  `read` int DEFAULT '0' COMMENT '是否已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `message` */

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `picture` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `invitation_code_id` bigint DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `gender` int DEFAULT '2',
  `phone` varchar(50) DEFAULT NULL,
  `honor_point` bigint DEFAULT NULL COMMENT '荣誉值',
  `self_control_point` bigint DEFAULT NULL COMMENT '自制力',
  `contribution_point` bigint DEFAULT NULL COMMENT '贡献值',
  `walk` bigint DEFAULT NULL,
  `read` bigint DEFAULT NULL,
  `sport` bigint DEFAULT NULL,
  `art` bigint DEFAULT NULL,
  `practice` bigint DEFAULT NULL,
  `valid` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
