-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-05-2024 a las 02:52:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `canpedro`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `llicencia`
--

CREATE TABLE `llicencia` (
  `llicencia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `llicencia`
--

INSERT INTO `llicencia` (`llicencia`) VALUES
('05yOz6KvD'),
('3YOxE8yqJ'),
('fu3T551Vt'),
('g7msmZ7j8'),
('GAaz24egY'),
('i9roj7vyT'),
('iiKwUFI9i'),
('jitmkgeZP'),
('loZJgTeV0'),
('mxCk7FgGI'),
('mydsFBtO9'),
('npY933jhw'),
('pA1j10atW'),
('q5Zo0x4uM'),
('qFktp0Zeu'),
('rHB8gJsdW'),
('TwzV5XEmT'),
('uFf2xwZiN'),
('wOnOsN8He'),
('y1PIo5NMP');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu`
--

CREATE TABLE `menu` (
  `nombre` int(11) NOT NULL,
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`json`)),
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`nombre`, `json`, `data`) VALUES
(1, '[{\"Plato\":\"Amanida amb formatge \",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"lactics\"},{\"Plato\":\"Truita de patates\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"ou\"},{\"Plato\":\"Canelons\",\"Precio\":6.0,\"Tipo\":\"primer\",\"Alérgenos\":\"lactics, carn, gluten\"},{\"Plato\":\"Coca de recapte\",\"Precio\":6.0,\"Tipo\":\"primer\",\"Alérgenos\":\"peix, gluten\"},{\"Plato\":\"Parrillada de verdures\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"\"},{\"Plato\":\"Sopa de pollastre\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Croquetes de cigrons\",\"Precio\":6.0,\"Tipo\":\"segon\",\"Alérgenos\":\"gluten, lactics\"},{\"Plato\":\"Bacallà a la llauna\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"peix\"},{\"Plato\":\"Entrecot al Roquefort\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"carn, lactics\"},{\"Plato\":\"Costelles de xai\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Calamars Farcits\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"peix, carns\"},{\"Plato\":\"Milfulls de boniato i bolets\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"\"},{\"Plato\":\"Macedònia de fruita\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"},{\"Plato\":\"Pastís de formatge\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"lactics, gluten, ou\"},{\"Plato\":\"Mousse de xocolata\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"ou\"},{\"Plato\":\"Sorbet de llimona\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"}]', '2024-03-26'),
(2, '[{\"Plato\":\"Amanida verda\",\"Precio\":5.0,\"Tipo\":\"primer\",\"Alérgenos\":\"\"},{\"Plato\":\"Escudella\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"carn, gluten\"},{\"Plato\":\"Macarrons a la catalana\",\"Precio\":6.0,\"Tipo\":\"primer\",\"Alérgenos\":\"lactics, carn, gluten, ou\"},{\"Plato\":\"Espinacs a la catalana\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"\"},{\"Plato\":\"Fabada asturiana\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Arrós a la cubana\",\"Precio\":5.5,\"Tipo\":\"primer\",\"Alérgenos\":\"ou\"},{\"Plato\":\"Canelons d’espinacs\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"gluten, lactics\"},{\"Plato\":\"Sardines al forn\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"peix\"},{\"Plato\":\"Llibrets de llom\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"carn, gluten\"},{\"Plato\":\"Bunyols de Bacallà\",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"peix\"},{\"Plato\":\"Botifarra amb mongetes\",\"Precio\":6.5,\"Tipo\":\"segon\",\"Alérgenos\":\"carn\"},{\"Plato\":\"Hamburguesa de remolatxa \",\"Precio\":7.0,\"Tipo\":\"segon\",\"Alérgenos\":\"\"},{\"Plato\":\"Macedònia de fruita\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"},{\"Plato\":\"Iogurt amb maduixes\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"lactics\"},{\"Plato\":\"Pastís de xocolata\",\"Precio\":1.5,\"Tipo\":\"postre\",\"Alérgenos\":\"gluten, ou\"},{\"Plato\":\"Sorbet de mandarina\",\"Precio\":1.0,\"Tipo\":\"postre\",\"Alérgenos\":\"\"}]', '2024-04-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `tableid` int(11) NOT NULL,
  `pedidojson` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`pedidojson`)),
  `isServido` tinyint(1) NOT NULL,
  `isPagado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `settings`
--

CREATE TABLE `settings` (
  `id` int(11) NOT NULL,
  `tema` tinyint(1) NOT NULL,
  `lang` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `settings`
--

INSERT INTO `settings` (`id`, `tema`, `lang`) VALUES
(1, 0, 'cat'),
(4, 0, 'es'),
(5, 0, 'es'),
(6, 0, 'es'),
(7, 0, 'es');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tipus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `password`, `tipus`) VALUES
(1, 'test', 'test1234', 'cambrer'),
(2, 'camarero', '123456', 'cambrer'),
(3, 'cocinero', '123456', 'cuiner');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `llicencia`
--
ALTER TABLE `llicencia`
  ADD PRIMARY KEY (`llicencia`);

--
-- Indices de la tabla `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`tableid`);

--
-- Indices de la tabla `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `menu`
--
ALTER TABLE `menu`
  MODIFY `nombre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `settings`
--
ALTER TABLE `settings`
  ADD CONSTRAINT `settings_ibfk_1` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
