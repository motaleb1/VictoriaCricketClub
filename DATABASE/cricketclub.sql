-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2019 at 07:43 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cricketclub`
--

-- --------------------------------------------------------

--
-- Table structure for table `expenditure`
--

CREATE TABLE IF NOT EXISTS `expenditure` (
  `ex_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_id` varchar(10) DEFAULT NULL,
  `ex_purpose` varchar(60) DEFAULT NULL,
  `ex_amount` int(11) DEFAULT NULL,
  `ex_payDate` date DEFAULT NULL,
  PRIMARY KEY (`ex_id`),
  KEY `fk_member` (`m_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `expenditure`
--

INSERT INTO `expenditure` (`ex_id`, `m_id`, `ex_purpose`, `ex_amount`, `ex_payDate`) VALUES
(1, '2', 'Poster', 400, '2019-02-14'),
(2, '2', 'Banner', 800, '2019-02-14');

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

CREATE TABLE IF NOT EXISTS `fees` (
  `fee_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_id` varchar(10) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  `purpose` varchar(20) NOT NULL,
  PRIMARY KEY (`fee_id`),
  KEY `fk_member` (`m_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `fees`
--

INSERT INTO `fees` (`fee_id`, `m_id`, `amount`, `payDate`, `purpose`) VALUES
(1, '1', 500, '2019-02-15', 'Registration'),
(2, '2', 500, '2019-02-14', 'Registration'),
(3, '3', 400, '2019-02-13', 'Registration'),
(4, '1', 100, '2019-02-14', 'Monthly Fee'),
(5, '2', 200, '2019-02-14', 'Match Fee'),
(6, '3', 200, '2019-02-14', 'Training Fee');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(20) NOT NULL,
  `password` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('kyau', 123),
('mis', 1234);

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE IF NOT EXISTS `matches` (
  `match_no` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(50) DEFAULT NULL,
  `opposition` varchar(50) DEFAULT NULL,
  `winner` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`match_no`),
  KEY `fk_team` (`t_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`match_no`, `t_name`, `opposition`, `winner`) VALUES
(1, 'CSE', 'Belkuchi', 'Belkuchi'),
(2, 'DBA', 'Belkuchi', 'DBA'),
(3, 'MIS', 'Kazipur', 'MIS');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `m_id` varchar(10) NOT NULL,
  `m_name` varchar(60) DEFAULT NULL,
  `m_address` varchar(160) DEFAULT NULL,
  `m_phone` varchar(13) DEFAULT NULL,
  `m_type` varchar(30) DEFAULT NULL,
  `regi_date` date DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`m_id`, `m_name`, `m_address`, `m_phone`, `m_type`, `regi_date`) VALUES
('1', 'MTA', 'Tangail', '0177845225', 'Adult', '2019-02-14'),
('2', 'Taher', 'Dhaka', '01785224', 'Player', '2019-02-14'),
('3', 'A. Khaleq', 'S.Ganj', '017854266', 'Player', '2019-02-13');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `p_id` varchar(10) NOT NULL,
  `m_id` varchar(10) DEFAULT NULL,
  `t_name` varchar(50) DEFAULT NULL,
  `p_level` varchar(20) DEFAULT NULL,
  `p_speciality` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `fk_member` (`m_id`),
  KEY `fk_team` (`t_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE IF NOT EXISTS `team` (
  `t_no` varchar(10) DEFAULT NULL,
  `t_name` varchar(50) NOT NULL,
  PRIMARY KEY (`t_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`t_no`, `t_name`) VALUES
('3', 'CSE'),
('2', 'DBA'),
('1', 'MIS');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `expenditure`
--
ALTER TABLE `expenditure`
  ADD CONSTRAINT `expenditure_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`);

--
-- Constraints for table `fees`
--
ALTER TABLE `fees`
  ADD CONSTRAINT `fees_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`);

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`t_name`) REFERENCES `team` (`t_name`);

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `player_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`),
  ADD CONSTRAINT `player_ibfk_2` FOREIGN KEY (`t_name`) REFERENCES `team` (`t_name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
