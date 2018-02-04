-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 04. Feb 2018 um 16:54
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
  `id` bigint(11) NOT NULL,
  `speciesid` int(11) NOT NULL,
  `description` varchar(300) NOT NULL,
  `longitude` decimal(10,2) NOT NULL,
  `latitude` decimal(10,2) NOT NULL,
  `sealevel` int(11) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `user` varchar(30) NOT NULL,
  `datetime` datetime NOT NULL,
  `enabled` varchar(1) NOT NULL,
  `image1` varchar(40) DEFAULT NULL,
  `image2` varchar(40) DEFAULT NULL,
  `image3` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- TRUNCATE Tabelle vor dem Einfügen `sighting`
--

TRUNCATE TABLE `sighting`;
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
  `image1` varchar(30) DEFAULT NULL,
  `image2` varchar(30) DEFAULT NULL,
  `image3` varchar(30) DEFAULT NULL,
  `image4` varchar(30) DEFAULT NULL,
  `image5` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- TRUNCATE Tabelle vor dem Einfügen `species`
--

TRUNCATE TABLE `species`;
--
-- Daten für Tabelle `species`
--

INSERT INTO `species` (`id`, `species`, `category`, `latinname`, `normalname`, `description`, `validfrom`, `validto`, `image1`, `image2`, `image3`, `image4`, `image5`) VALUES
(1, 'Wespenspinne', 'Tier', 'Latein', 'Spinne', 'Spinne die Wespen isst.', 1, 12, 'art_1_1.jpg', '', '', '', ''),
(2, 'Hund', 'Tier', 'Latein', 'Hund', 'Ist ein Hund.', 1, 12, 'art_2_1.jpg', 'art_2_2.jpg', NULL, NULL, NULL),
(3, 'Katze', 'Tier', 'Latein', 'Katze', 'Ist eine Katze.', 1, 12, 'art_3_1.jpg', 'art_3_2.jpg', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `enabled` varchar(1) NOT NULL,
  `adminflag` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- TRUNCATE Tabelle vor dem Einfügen `user`
--

TRUNCATE TABLE `user`;
--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`username`, `email`, `firstname`, `lastname`, `zip`, `city`, `password`, `enabled`, `adminflag`) VALUES
('max', 'msandberger@gmx.at', 'max', 'sand', '4040', 'linz', '1234', 'Y', 'Y');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `sighting`
--
ALTER TABLE `sighting`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_SpeciesId` (`speciesid`);

--
-- Indizes für die Tabelle `species`
--
ALTER TABLE `species`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `sighting`
--
ALTER TABLE `sighting`
  ADD CONSTRAINT `FK_SpeciesId` FOREIGN KEY (`speciesid`) REFERENCES `species` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
