-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-08-2020 a las 18:46:49
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `fakerest1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `amount` double NOT NULL,
  `description` varchar(256) NOT NULL,
  `minimumAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ingredients`
--

INSERT INTO `ingredients` (`id`, `name`, `amount`, `description`, `minimumAmount`) VALUES
(1, 'ing1 Test', 1, 'testDesc', 1),
(2, 'test ing 2', 20, 'test ing 2', 2),
(3, 'test ing 3', 30, 'test ing 3', 50),
(4, 'test ing 4', 40, 'test ing 4', 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`id`, `price`, `description`) VALUES
(1, 10, 'Menu de prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu_products`
--

CREATE TABLE `menu_products` (
  `menu_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `menu_products`
--

INSERT INTO `menu_products` (`menu_id`, `product_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(256) NOT NULL,
  `price` double NOT NULL,
  `priority` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `priority`) VALUES
(1, 'Prod test1', 'Desc test 1', 10, 1),
(2, 'Prod test2', 'Desc test 2', 20, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_ingredients`
--

CREATE TABLE `product_ingredients` (
  `productId` int(11) NOT NULL,
  `ingredientId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='relation between which ingredients does each product have';

--
-- Volcado de datos para la tabla `product_ingredients`
--

INSERT INTO `product_ingredients` (`productId`, `ingredientId`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tables`
--

CREATE TABLE `tables` (
  `code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tables`
--

INSERT INTO `tables` (`code`) VALUES
(123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(256) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `type`) VALUES
(1, 'admin@admin.com', 'admin', 0),
(2, 'sergio@admin.com', 'mag1cPassW0rd!*', 0),
(3, 'comensal@comensal.com', 'comensal', 1),
(4, 'waiter@waiter.com', 'waiter', 2),
(5, 'cook@cook.com', 'cook', 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indices de la tabla `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `menu_products`
--
ALTER TABLE `menu_products`
  ADD PRIMARY KEY (`menu_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `product_ingredients`
--
ALTER TABLE `product_ingredients`
  ADD PRIMARY KEY (`productId`,`ingredientId`),
  ADD KEY `ingredientId` (`ingredientId`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `menu_products`
--
ALTER TABLE `menu_products`
  ADD CONSTRAINT `menu_products_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `menu_products_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `product_ingredients`
--
ALTER TABLE `product_ingredients`
  ADD CONSTRAINT `product_ingredients_ibfk_1` FOREIGN KEY (`ingredientId`) REFERENCES `ingredients` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ingredients_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
