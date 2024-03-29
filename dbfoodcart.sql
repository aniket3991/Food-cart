-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: foodcart
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_cart`
--

DROP TABLE IF EXISTS `tbl_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `food_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `food_id` (`food_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tbl_cart_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `tbl_food` (`food_id`),
  CONSTRAINT `tbl_cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cart`
--

LOCK TABLES `tbl_cart` WRITE;
/*!40000 ALTER TABLE `tbl_cart` DISABLE KEYS */;
INSERT INTO `tbl_cart` VALUES (38,3,10,3),(39,5,21,3);
/*!40000 ALTER TABLE `tbl_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_food`
--

DROP TABLE IF EXISTS `tbl_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_food` (
  `food_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_food`
--

LOCK TABLES `tbl_food` WRITE;
/*!40000 ALTER TABLE `tbl_food` DISABLE KEYS */;
INSERT INTO `tbl_food` VALUES (3,'rice',45.00,479,'is used for daily meal'),(5,'apple',50.00,79,'apple is good to eat'),(6,'orange',15.00,990,'orange is orange in color');
/*!40000 ALTER TABLE `tbl_food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_order`
--

DROP TABLE IF EXISTS `tbl_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `food_id` int NOT NULL,
  `order_date` date NOT NULL,
  `order_status` varchar(20) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `food_id` (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_order`
--

LOCK TABLES `tbl_order` WRITE;
/*!40000 ALTER TABLE `tbl_order` DISABLE KEYS */;
INSERT INTO `tbl_order` VALUES (1,2,5,'2024-02-07','Ordered',50.00,10),(2,2,3,'2024-02-07','Ordered',45.00,5),(3,2,3,'2024-02-07','Ordered',45.00,10),(4,2,5,'2024-02-07','Ordered',50.00,10),(5,2,6,'2024-02-07','Ordered',15.00,10),(6,2,3,'2024-02-10','Ordered',45.00,1),(7,2,5,'2024-02-10','Ordered',50.00,11),(8,2,7,'2024-02-10','Ordered',5.00,5),(9,6,3,'2024-02-10','Ordered',45.00,10),(10,6,7,'2024-02-10','Ordered',5.00,5);
/*!40000 ALTER TABLE `tbl_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `activated_user` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'aniket','mishra','admin','ani@gmail.com','pass@123','khunti',1),(2,'aman','mishra','user','ani123@gmail.com','aman@123','ramgardh',1),(3,'aniket','mishra','admin','aniket@gmail.com','password','khunti',1),(4,'ria','biswas','admin','ria@gmail.com','ria123','kolkata',0),(7,'hi','hi','user','hello','hi','hi',0);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-27 23:23:59
