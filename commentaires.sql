-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 20, 2024 at 10:08 PM
-- Server version: 8.0.36-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `commentaires`
--

-- --------------------------------------------------------

--
-- Table structure for table `eventComments`
--

CREATE TABLE `eventComments` (
  `eventCommentID` int NOT NULL,
  `eventID` int NOT NULL,
  `userID` int NOT NULL,
  `replyTo` int DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `musicComments`
--

CREATE TABLE `musicComments` (
  `musicCommentID` int NOT NULL,
  `musicID` int NOT NULL,
  `userID` int NOT NULL,
  `replyTo` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `playlistComments`
--

CREATE TABLE `playlistComments` (
  `playlistCommentID` int NOT NULL,
  `playlistID` int NOT NULL,
  `userID` int NOT NULL,
  `replyTo` int DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `eventComments`
--
ALTER TABLE `eventComments`
  ADD PRIMARY KEY (`eventCommentID`),
  ADD KEY `replyTo` (`replyTo`);

--
-- Indexes for table `musicComments`
--
ALTER TABLE `musicComments`
  ADD PRIMARY KEY (`musicCommentID`),
  ADD KEY `replyTo` (`replyTo`);

--
-- Indexes for table `playlistComments`
--
ALTER TABLE `playlistComments`
  ADD PRIMARY KEY (`playlistCommentID`),
  ADD KEY `replyTo` (`replyTo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `eventComments`
--
ALTER TABLE `eventComments`
  MODIFY `eventCommentID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `musicComments`
--
ALTER TABLE `musicComments`
  MODIFY `musicCommentID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `playlistComments`
--
ALTER TABLE `playlistComments`
  MODIFY `playlistCommentID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `eventComments`
--
ALTER TABLE `eventComments`
  ADD CONSTRAINT `eventComments_ibfk_1` FOREIGN KEY (`replyTo`) REFERENCES `eventComments` (`eventCommentID`);

--
-- Constraints for table `musicComments`
--
ALTER TABLE `musicComments`
  ADD CONSTRAINT `musicComments_ibfk_1` FOREIGN KEY (`replyTo`) REFERENCES `musicComments` (`musicCommentID`);

--
-- Constraints for table `playlistComments`
--
ALTER TABLE `playlistComments`
  ADD CONSTRAINT `playlistComments_ibfk_1` FOREIGN KEY (`replyTo`) REFERENCES `playlistComments` (`playlistCommentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
