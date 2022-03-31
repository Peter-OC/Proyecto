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

-- Volcando datos para la tabla pizzeria.categories: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id_category`, `type`, `description`) VALUES
	(1, 'starter', 'los mejores entrantes'),
	(3, 'pizza', 'una rrrrrrrica piizzzaaaaaa ELTSGOO');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.comments: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`id_comment`, `id_user`, `id_product`, `text`, `date`, `score`) VALUES
	(1, 1, 1, 'jejeje ya va', '2022-03-16 00:00:00', 3),
	(2, 1, 1, 'Excelente', '2022-03-26 20:19:02', 2),
	(3, 1, 1, 'Malísimo', '2022-03-29 19:22:56', 3);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.ingredients: ~35 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` (`id_ingredient`, `type`, `name`, `price`, `photo`) VALUES
	(1, 'other', 'cebolla', 0.50, NULL),
	(2, 'other', 'mozzarella', 0.50, NULL),
	(3, 'other', 'tomate', 0.50, NULL),
	(4, 'other', 'jamon', 0.50, NULL),
	(5, 'other', 'pimiento', 0.50, NULL),
	(6, 'other', 'champiñon', 0.50, NULL),
	(7, 'other', 'aceitunas', 0.50, NULL),
	(8, 'other', 'aceitunas negras', 0.50, NULL),
	(9, 'other', 'pimiento', 0.50, NULL),
	(10, 'other', 'carne picada', 1.50, NULL),
	(11, 'other', 'bacon', 1.50, NULL),
	(12, 'other', 'pollo', 1.50, NULL),
	(13, 'other', 'calabacin', 0.50, NULL),
	(14, 'other', 'mozzarella', 0.50, NULL),
	(15, 'other', 'piña', 0.50, NULL),
	(16, 'other', 'huevo', 0.75, NULL),
	(17, 'other', 'peperoni', 0.50, NULL),
	(18, 'other', 'queso de cabra', 1.50, NULL),
	(19, 'other', 'queso provolone', 0.50, NULL),
	(20, 'other', 'queso azul', 1.00, NULL),
	(21, 'other', 'queso vegano', 1.00, NULL),
	(22, 'other', 'rucula', 0.50, NULL),
	(23, 'base', 'masa fina', 0.50, NULL),
	(24, 'base', 'masa gruesa', 1.00, NULL),
	(25, 'base', 'calzone', 1.00, NULL),
	(26, 'base', 'bordes rellenos', 2.00, NULL),
	(27, 'sauce', 'salsa carbonara', 1.50, NULL),
	(28, 'sauce', 'salsa bbq', 1.50, NULL),
	(29, 'sauce', 'salsa de tomate', 1.00, NULL),
	(30, 'sauce', 'salsa pesto', 1.75, NULL),
	(31, 'other', 'heura', 2.00, NULL),
	(32, 'other', 'soja texturizada', 1.25, NULL),
	(33, 'other', 'chorizo veggie', 1.25, NULL),
	(34, 'other', 'peperoni veggie', 1.25, NULL),
	(35, 'other', 'anchoas', 0.50, NULL),
	(36, 'other', 'mejillones', 0.50, NULL),
	(37, 'other', 'salmon', 2.50, NULL),
	(38, 'other', 'atun', 1.50, NULL),
	(39, 'other', 'guindilla', 0.50, NULL),
	(40, 'sauce', 'salsa kebab', 0.75, NULL);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.ingredients_per_pizza: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredients_per_pizza` DISABLE KEYS */;
INSERT INTO `ingredients_per_pizza` (`id_pizza`, `id_ingredient`, `amount`) VALUES
	(1, 7, 1),
	(2, 11, 2);
/*!40000 ALTER TABLE `ingredients_per_pizza` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.orders: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.pizzas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pizzas` DISABLE KEYS */;
INSERT INTO `pizzas` (`id_pizza`, `base`, `sauce`) VALUES
	(1, 35, 38),
	(2, 35, 38);
/*!40000 ALTER TABLE `pizzas` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.products: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id_product`, `id_pizza`, `id_category`, `id_comment`, `name`, `price`, `photo`, `description`, `thelike`, `dislike`) VALUES
	(1, NULL, 1, NULL, 'Pizza rica', 12.00, NULL, NULL, 0, 0),
	(2, 1, 3, NULL, 'Segundo prod', 11.00, NULL, NULL, 0, 0),
	(9, 1, 1, NULL, 'string', 11.00, 'string', 'string', 0, 0),
	(10, 2, 3, NULL, 'otra', 11.00, NULL, NULL, 0, 0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.products_per_order: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `products_per_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_per_order` ENABLE KEYS */;

-- Volcando datos para la tabla pizzeria.users: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id_user`, `first_name`, `last_name`, `email`, `address`, `password`, `function`) VALUES
	(1, 'Bibi', NULL, 'bibi@gmail.com', 'Avenida Libertad', 'pass12', 'user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
