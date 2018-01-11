-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 11. Jan 2018 um 15:28
-- Server-Version: 10.1.28-MariaDB
-- PHP-Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `sighting`
--

INSERT INTO `sighting` (`id`, `speciesid`, `description`, `longitude`, `latitude`, `sealevel`, `city`, `state`, `country`, `user`, `datetime`, `enabled`, `image1`, `image2`, `image3`) VALUES
(1, 1, 'gefunden', '443.00', '4345.00', 2, 'linz', 'ooe', 'oe', 'max', '2017-12-28 00:00:00', 'Y', '1_1.jpg', NULL, NULL),
(2, 1, 'GUT', '4.21', '2.22', 2, 'Traun', 'ooe', 'oe', 'samet', '2017-12-20 00:00:00', 'Y', '2_1', '2_2', NULL),
(5, 1, 'Tier', '4.00', '2.00', 2, 'Traun', 'ooe', 'oe', 'DU', '1970-01-01 00:00:00', 'Y', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `species`
--

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
-- Daten für Tabelle `species`
--

INSERT INTO `species` (`id`, `species`, `category`, `latinname`, `normalname`, `description`, `validfrom`, `validto`, `image1`) VALUES
(1, 'wespenspinne', 'tier', 'schaß', 'spinne', 'is ah spinne', 1, 12, '/1.jpg'),
(2, 'vogel', 'vog', 'mf', 'mdm', 'kdpsmdksml', 3, 12, '/1.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

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
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `sighting`
--
ALTER TABLE `sighting`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `sighting`
--
ALTER TABLE `sighting`
  ADD CONSTRAINT `FK_SpeciesId` FOREIGN KEY (`speciesid`) REFERENCES `species` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
