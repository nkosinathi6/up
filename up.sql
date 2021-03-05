-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 24, 2018 at 03:08 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `up`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `AdminID` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminID`, `userName`, `password`) VALUES
('1234', 'Nkosi', 'Nko@1234');

-- --------------------------------------------------------

--
-- Table structure for table `computer`
--

CREATE TABLE `computer` (
  `ComputerID` varchar(50) NOT NULL,
  `LabNo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `LogID` varchar(50) NOT NULL,
  `StudentNumber` varchar(50) NOT NULL,
  `USBID` varchar(50) NOT NULL,
  `ComputerID` varchar(50) NOT NULL,
  `dateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Status` enum('Lost','Found') NOT NULL DEFAULT 'Found'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`LogID`, `StudentNumber`, `USBID`, `ComputerID`, `dateTime`, `Status`) VALUES
('3119f37e-7e6d-4823-a97c-2fcc7e13876e', 'Nkosinathi', '737c3821-366e-472d-9a0e-0f36aaadc49c', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-22 18:20:28', 'Found'),
('377ca9f6-1006-4919-aac2-094c0a05620c', '214212483', '4b1efc75-727e-43dd-ab94-13f93df829ad', 'ComputerID101', '2018-07-17 10:10:41', 'Found'),
('3ed67266-b72f-48cc-9d1f-c143ab453df5', 'Nkosinathi', '33724f86-137a-40ed-bfd2-50dce3060e5f', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-23 10:54:26', 'Found'),
('45805f5e-da3d-443c-b5b1-d98d8244fe8a', 'Nkosinathi', '33724f86-137a-40ed-bfd2-50dce3060e5f', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-23 10:48:53', 'Found'),
('5603969f-c324-4d1f-ade2-7dd6089e20a2', 'Nkosinathi', '33724f86-137a-40ed-bfd2-50dce3060e5f', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-23 10:43:55', 'Found'),
('ab3f2ba4-353a-4b36-baa2-6efeab6e4ea0', 'Nkosinathi', '33724f86-137a-40ed-bfd2-50dce3060e5f', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-23 10:40:34', 'Found'),
('c671b555-119b-4cf3-8ded-b86038bbc76c', 'Nkosinathi', '8dea4420-4719-4cf7-8053-148c2fa757dc', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-22 17:56:44', 'Found'),
('d12b6f00-75eb-4be4-8d0c-347e894d131d', 'Nkosinathi', '8dea4420-4719-4cf7-8053-148c2fa757dc', 'DESKTOP-1RPLK3F/127.0.0.1', '2018-07-22 17:57:53', 'Found'),
('dafad478-98bc-48d8-b153-0f2c8479d573', 'Nkosinathi', '33724f86-137a-40ed-bfd2-50dce3060e5f', 'DESKTOP-1RPLK3F/10.122.138.135', '2018-07-23 10:56:55', 'Found'),
('LogID12', '214212483', '8db7e09c-8e32-45e6-bef2-fd62508c2b4a', 'ComputerID101', '2018-07-10 17:24:13', 'Found');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `StudentNumber` varchar(50) NOT NULL,
  `StudentName` varchar(50) NOT NULL,
  `StudentSurname` varchar(50) NOT NULL,
  `StudentEmail` text NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`StudentNumber`, `StudentName`, `StudentSurname`, `StudentEmail`, `Password`) VALUES
('214212483', 'Nko', 'Mada', 'Nko@gm.com', 'thebio93');

-- --------------------------------------------------------

--
-- Table structure for table `usb`
--

CREATE TABLE `usb` (
  `USBID` varchar(50) NOT NULL,
  `StudentNumber` varchar(50) NOT NULL,
  `friendlyName` text NOT NULL,
  `Status` enum('Lost','Found') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usb`
--

INSERT INTO `usb` (`USBID`, `StudentNumber`, `friendlyName`, `Status`) VALUES
('140080e2-8be0-4a10-bc0e-b394a32ac34c', '214212483', 'friendlyName', 'Lost'),
('33724f86-137a-40ed-bfd2-50dce3060e5f', 'Nkosinathi', 'Black 2GB USB', 'Lost'),
('38ce4cd5-6481-425d-8755-4c17e2f857f2', '214212483', 'friendlyName', 'Found'),
('4b1efc75-727e-43dd-ab94-13f93df829ad', '214212483', 'friendlyName', 'Found'),
('4f0149e0-dfe8-45d0-b3ca-4e2be25f9b8a', '214212483', 'friendlyName', 'Found'),
('5f9a6e8b-465c-407a-9008-8ef0425a0336', '214212483', 'friendlyName', 'Found'),
('6a28e802-78de-46e1-a0f9-2631a13412c5', 'Nkosinathi', 'Black NMBM 2GB', 'Found'),
('737c3821-366e-472d-9a0e-0f36aaadc49c', 'Nkosinathi', 'jg', 'Lost'),
('8db7e09c-8e32-45e6-bef2-fd62508c2b4a', '214212483', 'friendlyName', 'Found'),
('8dea4420-4719-4cf7-8053-148c2fa757dc', 'Nkosinathi', 'Black', 'Found'),
('8f686e3f-ccb6-4181-be47-bea244d0a928', '214212483', 'friendlyName', 'Found'),
('940c8e32-fc22-43ac-87ee-38cf0f137a4d', '214212483', 'friendlyName', 'Found'),
('c22294ae-5e37-4f16-9e07-f2d51b142844', '214212483', 'friendlyName', 'Found');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`AdminID`);

--
-- Indexes for table `computer`
--
ALTER TABLE `computer`
  ADD PRIMARY KEY (`ComputerID`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`LogID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`StudentNumber`);

--
-- Indexes for table `usb`
--
ALTER TABLE `usb`
  ADD PRIMARY KEY (`USBID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
