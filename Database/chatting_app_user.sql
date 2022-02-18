-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chatting_app
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `User_ID` int NOT NULL AUTO_INCREMENT,
  `PHONE_NUMBER` varchar(45) NOT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `GENDER` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `PICTURE` varchar(1000) DEFAULT NULL,
  `COUNTRY` varchar(45) DEFAULT NULL,
  `BIO` varchar(250) DEFAULT NULL,
  `STATUS` int DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `PHONE_NUMBER_UNIQUE` (`PHONE_NUMBER`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'333333355','Samyy','951357','male','Samy@hotmail.com','url','Cairo','HI lets talk',2,'1997-03-31'),(2,'22222','Hend','951357','male','Hend@hotmail.com','url','Cairo',NULL,1,'1997-03-31'),(3,'333333','Marwa','02221222','male','Marwaa@hotmail.com',NULL,NULL,NULL,1,NULL),(4,'98712345','Samyyy','11111',NULL,'samy@hotmail.com',NULL,'Egypt',NULL,1,NULL),(5,'01020354036','abdelazeez','123456',NULL,'abdelazeez@gmail.com',NULL,NULL,NULL,1,NULL),(6,'0123333112','Islam Samy','1234',NULL,'Islam@gmail.com',NULL,'Egypt','hii lest\'s talk',1,NULL),(7,'011122556669','Rick Sunches','12304','Male','RickSunches@gmail.com','','WorldOfRick','Wubbalubbadubdub!',1,'2015-02-18'),(8,'0111225788','Rick Sunches','12304','Male','RickSunches@gmail.com','src/main/resources/clientPictures/user0.jpg','WorldOfRick','Wubbalubbadubdub!',1,'2015-02-18'),(9,'012341235','Walter White','1234','Male','walter@gmail.com','src/main/resources/clientPictures/user0.jpg','Usa','Say my name ',1,'2014-02-04'),(10,'0123654556','heheehehe','12422','Male','hehehehe@gmail.com','src/main/resources/clientPictures/user0123654556.jpg','heheheehWorld',NULL,1,'2018-02-13'),(11,'1234','simson','1234','Male','simson@gmail.com','src/main/resources/clientPictures/user1234.jpg',NULL,NULL,1,'1999-02-18');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 10:06:53
