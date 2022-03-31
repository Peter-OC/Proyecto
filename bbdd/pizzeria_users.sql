-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para pizzeria_users
CREATE DATABASE IF NOT EXISTS `pizzeria_users` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizzeria_users`;

-- Volcando estructura para tabla pizzeria_users.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(250) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `first_name` varchar(25) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `last_name` varchar(25) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `address` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `roles` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria_users.users: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `first_name`, `last_name`, `address`, `password`, `roles`) VALUES
	('armandobronca@correo.com', 'Armando', 'Broncas', 'Calle de la Gresca', '$2a$10$0vkX3EGKlzE/RjarSzj/VuFVelHAYDoLZyz29vAOb22SIg52gBZoq', 'ROLE_ADMIN'),
	('kk@kk', 'pepito', 'grillo', 'otra', '$2a$10$DvCGeC2LVXik2ADm1/GOrODadtRKKSPRxTaOt5y0GbBTzsWPrOQIK', 'ROLE_USER,ROLE_EMPLOYED');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
