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


-- Volcando estructura de base de datos para pizzeria
CREATE DATABASE IF NOT EXISTS `pizzeria` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizzeria`;

-- Volcando estructura para tabla pizzeria.category
CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int unsigned NOT NULL AUTO_INCREMENT,
  `type` enum('bebida','entrante','pizza') CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `description` text CHARACTER SET latin1 COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.category: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.comment
CREATE TABLE IF NOT EXISTS `comment` (
  `id_comment` int unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int unsigned NOT NULL DEFAULT '0',
  `id_product` int unsigned NOT NULL,
  `text` text COLLATE latin1_spanish_ci NOT NULL,
  `date` date NOT NULL,
  `score` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_comment`) USING BTREE,
  KEY `userFK` (`id_user`),
  KEY `productFK` (`id_product`),
  CONSTRAINT `productFK` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  CONSTRAINT `userFK` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.comment: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredient
CREATE TABLE IF NOT EXISTS `ingredient` (
  `id_ingredient` int unsigned NOT NULL AUTO_INCREMENT,
  `type` enum('base','salsa','otro') CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `name` varchar(25) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `price` decimal(4,2) unsigned NOT NULL,
  `photo` varchar(200) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_ingredient`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.ingredient: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredients_per_pizza
CREATE TABLE IF NOT EXISTS `ingredients_per_pizza` (
  `id_pizza` int unsigned NOT NULL,
  `id_ingredient` int unsigned NOT NULL,
  `amount` int unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_pizza`,`id_ingredient`) USING BTREE,
  KEY `ingredienteFK` (`id_ingredient`) USING BTREE,
  CONSTRAINT `ingredientFK` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id_ingredient`),
  CONSTRAINT `pizzaFK` FOREIGN KEY (`id_pizza`) REFERENCES `pizza` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.ingredients_per_pizza: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredients_per_pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredients_per_pizza` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.order
CREATE TABLE IF NOT EXISTS `order` (
  `id_order` int unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int unsigned DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `address` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `price` decimal(5,2) unsigned DEFAULT NULL,
  `status_order` enum('ordered','in_process','ready','sent','received','canceled') COLLATE latin1_spanish_ci NOT NULL DEFAULT 'ordered',
  PRIMARY KEY (`id_order`),
  KEY `order_user_FK` (`id_user`),
  CONSTRAINT `order_user_FK` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.order: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pizza
CREATE TABLE IF NOT EXISTS `pizza` (
  `id_pizza` int unsigned NOT NULL AUTO_INCREMENT,
  `base` int unsigned NOT NULL,
  `sauce` int unsigned NOT NULL,
  PRIMARY KEY (`id_pizza`),
  KEY `baseFK` (`base`),
  KEY `salsaFK` (`sauce`) USING BTREE,
  CONSTRAINT `baseFK` FOREIGN KEY (`base`) REFERENCES `ingredient` (`id_ingredient`),
  CONSTRAINT `sauceFK` FOREIGN KEY (`sauce`) REFERENCES `ingredient` (`id_ingredient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.pizza: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.product
CREATE TABLE IF NOT EXISTS `product` (
  `id_product` int unsigned NOT NULL AUTO_INCREMENT,
  `id_pizza` int unsigned DEFAULT NULL,
  `id_category` int unsigned NOT NULL,
  `id_comment` int unsigned DEFAULT NULL,
  `name` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `price` decimal(4,2) unsigned NOT NULL,
  `photo` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `description` text COLLATE latin1_spanish_ci,
  `like` int unsigned DEFAULT '0',
  `dislike` int unsigned DEFAULT '0',
  PRIMARY KEY (`id_product`) USING BTREE,
  KEY `pizza_product_fk` (`id_pizza`),
  KEY `product_category_FK` (`id_category`),
  KEY `product_comment_FK` (`id_comment`),
  CONSTRAINT `pizza_product_fk` FOREIGN KEY (`id_pizza`) REFERENCES `pizza` (`id_pizza`),
  CONSTRAINT `product_category_FK` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `product_comment_FK` FOREIGN KEY (`id_comment`) REFERENCES `comment` (`id_comment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.product: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.products_per_order
CREATE TABLE IF NOT EXISTS `products_per_order` (
  `id_product` int unsigned NOT NULL,
  `id_order` int unsigned NOT NULL,
  `amount` int unsigned NOT NULL,
  PRIMARY KEY (`id_product`,`id_order`),
  KEY `FK_products_per_order_order` (`id_order`),
  CONSTRAINT `FK__product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  CONSTRAINT `FK_products_per_order_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.products_per_order: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `products_per_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_per_order` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `last_name` varchar(25) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `address` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `function` enum('user','staff','rider','manager') CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.user: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
