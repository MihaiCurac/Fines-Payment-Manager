
--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drivers` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `driving_license` varchar(255) NOT NULL,
  `identity_card` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `driving_license` (`driving_license`),
  UNIQUE KEY `identity_card` (`identity_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` VALUES (1,'Mihai Curac','Iuliu Maniu 1','143243253241','5020523524353'),(2,'Andrei Pop','Baritiu 66','235235235254','3242352352355'),(3,'Marius Ghenea','Transilvaniei 89','325252534534','2352352345324'),(4,'Mircea Popescu','Traian 189','235423523','457457457'),(5,'Test','Test 189','4363466','53456346');
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fines`
--

DROP TABLE IF EXISTS `fines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fines` (
  `id` int NOT NULL,
  `driver_id` int NOT NULL,
  `police_station_id` int DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `police_station_id` (`police_station_id`),
  CONSTRAINT `fines_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`),
  CONSTRAINT `fines_ibfk_2` FOREIGN KEY (`police_station_id`) REFERENCES `police_stations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `fines` WRITE;
/*!40000 ALTER TABLE `fines` DISABLE KEYS */;
INSERT INTO `fines` VALUES (1,1,1,'Speeding',155.00,'Paid'),(2,2,1,'Tailgating',500.00,'Unpaid'),(3,2,2,'Running a Red Light',1750.00,'Paid'),(4,4,1,'Tailgating',450.00,'Paid'),(5,5,2,'Speeding',1450.00,'Paid'),(6,4,1,'Tailgating',4150.00,'Paid');
/*!40000 ALTER TABLE `fines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `police_stations`
--

DROP TABLE IF EXISTS `police_stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `police_stations` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `police_stations` WRITE;
/*!40000 ALTER TABLE `police_stations` DISABLE KEYS */;
INSERT INTO `police_stations` VALUES (1,'Sectia 6 Cluj-Napoca','Observatorului 1','0123456789'),(2,'Sectia 3 Baia Mare','Iuliu Maniu','0891489178'),(3,'Sectia 2 Cluj-Napoca','21 Decembrie 1989','0712421419');
/*!40000 ALTER TABLE `police_stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `police_station_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `police_station_id` (`police_station_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`police_station_id`) REFERENCES `police_stations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'police1@gmail.com','cG9saWNlMQ==','police',1),(2,'police2@yahoo.com','cG9saWNlMg==','police',2),(3,'post1@gmail.com','cG9zdDE=','post_office',NULL),(4,'post2@yahoo.com','cG9zdDI=','post_office',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

