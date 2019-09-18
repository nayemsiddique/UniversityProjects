-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `event_info`
--

DROP TABLE IF EXISTS `event_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_info` (
  `name` varchar(100) NOT NULL,
  `event_date` date NOT NULL,
  `start_reg` date NOT NULL,
  `end_reg` date NOT NULL,
  `event_fee` int(10) NOT NULL,
  `event_code` varchar(25) NOT NULL,
  `isactive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`event_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_info`
--

LOCK TABLES `event_info` WRITE;
/*!40000 ALTER TABLE `event_info` DISABLE KEYS */;
INSERT INTO `event_info` VALUES ('cse Fest','2018-05-15','2018-05-01','2018-05-14',400,'csefest2018',1),('party','2018-05-22','2018-05-01','2018-05-20',300,'party22',1),('picnic','2018-05-14','2018-05-01','2018-05-13',1500,'pic2018',1);
/*!40000 ALTER TABLE `event_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_reg_info`
--

DROP TABLE IF EXISTS `event_reg_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_reg_info` (
  `username` varchar(25) NOT NULL,
  `event_code` varchar(25) NOT NULL,
  `reg_date` date DEFAULT NULL,
  `quota_type` varchar(15) NOT NULL,
  `event_fee` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_reg_info`
--

LOCK TABLES `event_reg_info` WRITE;
/*!40000 ALTER TABLE `event_reg_info` DISABLE KEYS */;
INSERT INTO `event_reg_info` VALUES ('nayem','csefest2018','2018-05-01','student',500),('nayem','pic2018','2018-05-01','student',1620),('nayem','1500','2018-05-02','teacher',2100),('nayem.asif','1500','2018-05-03','student',1550),('nayem','party22','2018-05-03','null',400),('nayem.asif','party22','2018-05-05','other',350);
/*!40000 ALTER TABLE `event_reg_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quota`
--

DROP TABLE IF EXISTS `quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quota` (
  `quota_type` varchar(15) NOT NULL,
  `quota_fee` int(10) NOT NULL,
  `event_code` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quota`
--

LOCK TABLES `quota` WRITE;
/*!40000 ALTER TABLE `quota` DISABLE KEYS */;
INSERT INTO `quota` VALUES ('student',1500,'pic2018'),('teacher',2000,'pic2018'),('Mlss',1000,'pic2018'),('student',400,'csefest2018'),('teacher',600,'csefest2018'),('Mlss',300,'csefest2018'),('Friend',300,'party22'),('Teacher',500,'party22');
/*!40000 ALTER TABLE `quota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_event_info`
--

DROP TABLE IF EXISTS `sub_event_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_event_info` (
  `event_code` varchar(25) NOT NULL,
  `sub_event_name` varchar(40) NOT NULL,
  `sub_event_fee` int(10) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_event_info`
--

LOCK TABLES `sub_event_info` WRITE;
/*!40000 ALTER TABLE `sub_event_info` DISABLE KEYS */;
INSERT INTO `sub_event_info` VALUES ('pic2018','game',50,'11:30:00','12:50:00'),('pic2018','race',70,'10:20:00','11:50:00'),('csefest2018','gamming',50,'09:30:00','12:00:00'),('csefest2018','programming contest',50,'10:00:00','12:30:00'),('party22','music',20,'11:00:00','12:50:00'),('party22','dance',50,'11:30:00','12:50:00');
/*!40000 ALTER TABLE `sub_event_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_event_reg_info`
--

DROP TABLE IF EXISTS `sub_event_reg_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_event_reg_info` (
  `sub_event_name` varchar(40) NOT NULL,
  `sub_event_fee` int(10) NOT NULL,
  `event_code` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_event_reg_info`
--

LOCK TABLES `sub_event_reg_info` WRITE;
/*!40000 ALTER TABLE `sub_event_reg_info` DISABLE KEYS */;
INSERT INTO `sub_event_reg_info` VALUES ('game',50,'pic2018','nayem'),('race',70,'pic2018','nayem'),('gamming',50,'csefest2018','nayem'),('programming contest',50,'csefest2018','nayem'),('race',50,'1500','nayem'),('quiz',50,'1500','nayem'),('race',50,'1500','nayem.asif'),('gamming',50,'csefest2018','nayem.asif'),('quiz',50,'1500','nayem.asif'),('music',20,'party22','nayem'),('dance',50,'party22','nayem'),('dance',50,'party22','nayem.asif');
/*!40000 ALTER TABLE `sub_event_reg_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(25) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','21232f297a57a5a743894a0e4a801fc3'),('asif','827ccb0eea8a706c4c34a16891f84e7b'),('nayem','827ccb0eea8a706c4c34a16891f84e7b'),('nayem.asif','827ccb0eea8a706c4c34a16891f84e7b');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `username` varchar(25) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(25) NOT NULL,
  `gender` enum('male','female','other') NOT NULL,
  `pin` varchar(25) NOT NULL,
  `dofb` date NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES ('asif','nayem','siddique','0168','nayemsidd@gmail.com','dhaka','male','1001','1995-1-21'),('nayem','Nayem','Siddique','017','nayem@gmail.com','dhaka','male','1212','1995-1-21'),('nayem.asif','Nayem','Siddique','0168','nayemsiddi@gmail.com','dhaka','male','1010','1996-1-11');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-06 19:36:08
