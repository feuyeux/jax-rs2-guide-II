DROP DATABASE IF EXISTS `simple_service_book`;
CREATE DATABASE `simple_service_book`;
USE `simple_service_book`;
CREATE TABLE `simple_book` (
  `BOOKID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOKNAME` varchar(128) DEFAULT NULL,
  `PUBLISHER` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`BOOKID`),
  UNIQUE KEY `BOOKID` (`BOOKID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `simple_book` WRITE;
INSERT INTO `simple_book` VALUES (1,'Java Restful Web Service实战','cmpbook'),
(2,'JSF2和RichFaces4使用指南','phei');
UNLOCK TABLES;

CREATE TABLE `user_roles` (
  `user_name` varchar(15) NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `user_roles` WRITE;
INSERT INTO `user_roles` VALUES ('caroline','user'),('eric','admin');
UNLOCK TABLES;

CREATE TABLE `users` (
  `user_name` varchar(15) NOT NULL,
  `user_pass` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('caroline','zhang'),('eric','han');
UNLOCK TABLES;