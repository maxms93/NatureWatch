-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 16. Dez 2017 um 15:51
-- Server-Version: 10.1.21-MariaDB
-- PHP-Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `naturewatch`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sighting`
--

DROP TABLE IF EXISTS `sighting`;
CREATE TABLE `sighting` (
  `id` int(11) NOT NULL,
  `speciesid` int(11) NOT NULL,
  `description` varchar(300) NOT NULL,
  `longitude` decimal(10,0) NOT NULL,
  `latitude` decimal(10,0) NOT NULL,
  `sealevel` int(11) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `user` varchar(30) NOT NULL,
  `datetime` datetime NOT NULL,
  `enabled` varchar(1) NOT NULL,
  `image1` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `sighting`
--

TRUNCATE TABLE `sighting`;
--
-- Daten für Tabelle `sighting`
--

INSERT INTO `sighting` (`id`, `speciesid`, `description`, `longitude`, `latitude`, `sealevel`, `city`, `state`, `country`, `user`, `datetime`, `enabled`, `image1`) VALUES
(1, 1, 'gefunden', '443', '4345', 2, 'linz', 'ooe', 'oe', 'max', '2017-12-28 00:00:00', 'Y', '/1.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `species`
--

DROP TABLE IF EXISTS `species`;
CREATE TABLE `species` (
  `id` int(11) NOT NULL,
  `species` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `latinname` varchar(30) NOT NULL,
  `normalname` varchar(30) NOT NULL,
  `description` varchar(500) NOT NULL,
  `validfrom` int(11) NOT NULL,
  `validto` int(11) NOT NULL,
  `image1` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `species`
--

TRUNCATE TABLE `species`;
--
-- Daten für Tabelle `species`
--

INSERT INTO `species` (`id`, `species`, `category`, `latinname`, `normalname`, `description`, `validfrom`, `validto`, `image1`) VALUES
(1, 'wespenspinne', 'tier', 'schaß', 'spinne', 'is ah spinne', 1, 12, '/1.jpg'),
(2, 'vogel', 'vog', 'mf', 'mdm', 'kdpsmdksml', 3, 12, '/1.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `zip` varchar(10) NOT NULL,
  `city` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `enabled` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `user`
--

TRUNCATE TABLE `user`;
--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`username`, `email`, `firstname`, `lastname`, `zip`, `city`, `password`, `enabled`) VALUES
('max', 'msandberger@gmx.at', 'max', 'sand', '4040', 'linz', '1234', 'Y');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `sighting`
--
ALTER TABLE `sighting`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `species`
--
ALTER TABLE `species`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
