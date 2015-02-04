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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `partnumber` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `manufacturerid` varchar(50) NOT NULL,
  `unitprice` double NOT NULL,
  PRIMARY KEY (`partnumber`),
  KEY `manufacturerid_idx` (`manufacturerid`),
  CONSTRAINT `manufacturerid` FOREIGN KEY (`manufacturerid`) REFERENCES `manufacturer` (`manufacturerid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('0123','Steering wheel','M9012',200),('0124','Valve','M9012',120),('0125','Steering wheel A','M8901',300),('0234','Bumper','M8901',500),('1234','Gas tank','M1234',300),('1345','Window','M1456',150),('1369','Air vent','M1234',250),('2012','Antenna','M1234',80),('2147','Emergency Brakes','M0123',125),('2345','Exhaust System','M0123',450),('2456','Parking Brakes','M1345',250),('3258','Air-conditioner','M1234',800),('3456','Clutch','M1345',250),('3457','Seat','M3456',400),('3567','Head Light','M4567',300),('4567','Seat Belt','M2345',150),('4678','Gear Stick','M0123',150),('5456','Oil Filter','M1345',120),('5678','Air bags','M1567',300),('6123','Pedal','M5678',130),('6258','Chasis','M0123',3000),('6789','Spark plug','M0123',80),('7123','Wind Shield','M0123',450),('7258','Engine','M1567',3500),('7456','Radio','M1456',240),('7890','Cooling system','M4567',200),('8321','Gear Box','M1456',1200),('9654','Tachometer','M1678',300);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
