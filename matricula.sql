-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2021 at 09:18 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `matricula`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Mostrar_Alumnos` ()  BEGIN

SELECT *

FROM alumnos;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Registrar_Alumnos` (IN `_codigo` VARCHAR(150), IN `_apellidos` VARCHAR(150), IN `_nombres` VARCHAR(150), IN `_direccion` VARCHAR(200), IN `_distrito` VARCHAR(200))  INSERT INTO alumnos (codigo, apellidos, nombres, direccion, distrito) VALUES (_codigo, _apellidos, _nombres, _direccion, _distrito)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `alumnos`
--

CREATE TABLE `alumnos` (
  `codigo` varchar(150) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `nombres` varchar(150) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `distrito` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `alumnos`
--

INSERT INTO `alumnos` (`codigo`, `apellidos`, `nombres`, `direccion`, `distrito`) VALUES
('A101', 'Lume', 'Arturo', 'Direccion1', 'Distrito1'),
('A102', 'Apellido', 'Nombre', 'Direccion2', 'Distrito2'),
('A103', 'Apellido1', 'Nombre1', 'Direccion1', 'Distrito1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
