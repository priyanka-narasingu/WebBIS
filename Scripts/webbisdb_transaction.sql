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
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transactionnumber` int(11) NOT NULL AUTO_INCREMENT,
  `transactiondate` datetime NOT NULL,
  `userid` varchar(50) NOT NULL,
  `partnumber` varchar(50) NOT NULL,
  `unitprice` double DEFAULT NULL,
  `sellingprice` double DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `totalprice` double DEFAULT NULL,
  `manufacturerconsumerid` varchar(50) NOT NULL,
  `inoutflag` int(11) NOT NULL,
  `manufacturerflag` int(11) NOT NULL,
  `damageditemflag` int(11) NOT NULL,
  PRIMARY KEY (`transactionnumber`),
  KEY `usercode_idx` (`userid`),
  KEY `partnumber_idx` (`partnumber`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2014-06-03 08:00:00','S0001','0123',250,300,10,3000,'C4455',0,0,0),(2,'2014-06-03 08:00:00','S0002','0234',120,150,20,3000,'C2233',0,0,0),(3,'2014-06-03 08:00:00','S0003','3456',150,200,30,6000,'C1122',0,0,0),(4,'2014-06-03 08:00:00','S0001','4567',250,300,15,4500,'C4455',0,0,0),(5,'2014-06-03 08:00:00','S0002','5678',300,NULL,5,NULL,'M1234',0,1,1),(6,'2014-06-03 08:00:00','S0001','0123',250,NULL,6,NULL,'M2345',0,1,1),(7,'2014-06-03 08:00:00','S0003','0234',120,NULL,10,NULL,'M1234',1,1,0),(8,'2014-06-03 08:00:00','S0002','3456',150,NULL,10,NULL,'M1234',1,1,0),(9,'2014-06-03 08:00:00','S0001','4567',250,NULL,20,NULL,'M2345',1,1,0),(10,'2014-06-03 08:00:00','S0001','5678',300,NULL,20,NULL,'M2345',1,1,0),(11,'2014-06-04 08:00:00','S0003','1234',200,NULL,20,NULL,'M1234',1,1,0),(12,'2014-06-05 08:00:00','S0001','4567',150,300,15,2250,'M4455',0,0,0),(13,'2014-06-06 08:00:00','S0003','3456',150,200,30,6000,'C1122',0,0,0),(14,'2014-06-07 08:00:00','S0003','3456',150,200,30,6000,'C1122',0,0,0),(15,'2014-06-07 08:00:00','S0001','4567',250,300,15,4500,'C4455',0,0,0),(16,'2014-06-07 08:00:00','S0002','5678',300,NULL,5,NULL,'M1234',0,1,1),(17,'2014-06-08 08:00:00','S0001','0123',250,NULL,6,NULL,'M2345',0,1,1),(18,'2014-06-08 08:00:00','S0003','0234',120,NULL,10,NULL,'M1234',1,1,0),(19,'2014-06-08 08:00:00','S0002','3456',150,NULL,10,NULL,'M1234',1,1,0),(20,'2014-06-09 08:00:00','S0002','0234',120,150,20,3000,'C2233',0,0,0),(21,'2014-06-09 08:00:00','S0003','3456',150,200,30,6000,'C1122',0,0,0),(22,'2014-06-09 08:00:00','S0001','4567',250,300,15,4500,'C4455',0,0,0),(23,'2014-06-09 08:00:00','S0002','5678',300,NULL,5,NULL,'M1234',0,1,1),(24,'2014-06-10 08:00:00','S0001','0123',250,NULL,6,NULL,'M2345',0,1,1),(25,'2014-06-10 08:00:00','S0003','0234',120,NULL,10,NULL,'M1234',1,1,0),(26,'2014-06-10 08:00:00','S0002','3456',150,NULL,10,NULL,'M1234',1,1,0);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
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
