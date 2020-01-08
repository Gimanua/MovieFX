-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Värd: 127.0.0.1
-- Tid vid skapande: 08 jan 2020 kl 23:45
-- Serverversion: 10.4.10-MariaDB
-- PHP-version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `movie_db`
--
CREATE DATABASE IF NOT EXISTS `movie_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `movie_db`;

-- --------------------------------------------------------

--
-- Tabellstruktur `directors`
--

CREATE TABLE `directors` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `directors`
--

INSERT INTO `directors` (`id`, `name`) VALUES
(2, 'George Lucas'),
(5, 'Christopher Nolan'),
(6, 'Peter Jackson');

-- --------------------------------------------------------

--
-- Tabellstruktur `genres`
--

CREATE TABLE `genres` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `genres`
--

INSERT INTO `genres` (`id`, `name`) VALUES
(1, 'Action'),
(6, 'Drama'),
(2, 'Fantasy'),
(7, 'Kriminal'),
(5, 'Rymdopera'),
(3, 'Sci-Fi'),
(8, 'Thriller'),
(4, 'Äventyr');

-- --------------------------------------------------------

--
-- Tabellstruktur `movies`
--

CREATE TABLE `movies` (
  `id` int(10) UNSIGNED NOT NULL,
  `title` varchar(128) NOT NULL,
  `grade` double NOT NULL,
  `length` time NOT NULL,
  `release_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `budget` bigint(20) NOT NULL,
  `revenue` bigint(20) NOT NULL,
  `director_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `movies`
--

INSERT INTO `movies` (`id`, `title`, `grade`, `length`, `release_date`, `budget`, `revenue`, `director_id`) VALUES
(4, 'Batman Begins', 8, '02:20:00', '2005-06-14 22:00:00', 150000000, 374218673, 5),
(5, 'The Dark Knight', 9, '02:32:00', '2008-07-13 22:00:00', 185000000, 1004558444, 5),
(6, 'The Lord of the Rings: The Return of the King', 9, '03:21:00', '2003-11-30 23:00:00', 94000000, 1119929521, 6);

-- --------------------------------------------------------

--
-- Tabellstruktur `movies_genres`
--

CREATE TABLE `movies_genres` (
  `movie_id` int(10) UNSIGNED NOT NULL,
  `genre_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumpning av Data i tabell `movies_genres`
--

INSERT INTO `movies_genres` (`movie_id`, `genre_id`) VALUES
(4, 1),
(4, 6),
(4, 7),
(4, 8),
(5, 1),
(5, 7),
(5, 8),
(6, 2),
(6, 4);

-- --------------------------------------------------------

--
-- Ersättningsstruktur för vy `movie_genres`
-- (See below for the actual view)
--
CREATE TABLE `movie_genres` (
`movie_id` int(10) unsigned
,`genre_id` int(10) unsigned
,`genre_name` varchar(64)
);

-- --------------------------------------------------------

--
-- Struktur för vy `movie_genres`
--
DROP TABLE IF EXISTS `movie_genres`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `movie_genres`  AS  select `movies`.`id` AS `movie_id`,`genres`.`id` AS `genre_id`,`genres`.`name` AS `genre_name` from ((`movies` join `genres`) join `movies_genres`) where `movies_genres`.`movie_id` = `movies`.`id` and `movies_genres`.`genre_id` = `genres`.`id` ;

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `directors`
--
ALTER TABLE `directors`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index för tabell `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `director_id` (`director_id`);

--
-- Index för tabell `movies_genres`
--
ALTER TABLE `movies_genres`
  ADD PRIMARY KEY (`movie_id`,`genre_id`),
  ADD KEY `genre_id` (`genre_id`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `directors`
--
ALTER TABLE `directors`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT för tabell `genres`
--
ALTER TABLE `genres`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT för tabell `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`director_id`) REFERENCES `directors` (`id`);

--
-- Restriktioner för tabell `movies_genres`
--
ALTER TABLE `movies_genres`
  ADD CONSTRAINT `movies_genres_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `movies_genres_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
