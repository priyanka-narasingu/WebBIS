CREATE DATABASE  IF NOT EXISTS `webbisdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `webbisdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: webbisdb
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `partnumber` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `reorderpoint` int(11) NOT NULL,
  `minreorderqty` int(11) NOT NULL,
  `orderamount` int(11) NOT NULL,
  `shelflocation` varchar(50) DEFAULT NULL,
  `damageditemscount` int(11) DEFAULT NULL,
  PRIMARY KEY (`partnumber`),
  KEY `partnumber_idx` (`partnumber`),
  CONSTRAINT `partnumber` FOREIGN KEY (`partnumber`) REFERENCES `product` (`partnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('0123',54,30,5,0,'Shelf A',6),('0124',20,30,20,0,'Shelf B',0),('0125',30,50,30,0,'Shelf C',0),('0234',35,30,10,0,'Shelf B',0),('2345',15,25,20,0,'Shelf A',0),('3456',35,30,5,30,'Shelf B',0),('4567',77,30,10,0,'Shelf A',8),('5678',87,30,10,0,'Shelf C',0),('6123',15,30,20,0,'Shelf A',0),('6789',10,20,10,0,'Shelf B',0),('7258',16,25,15,0,'Shelf B',0),('7890',20,30,20,0,'Shelf C',0),('8321',12,35,10,0,'Shelf C',0);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-17 11:23:29
