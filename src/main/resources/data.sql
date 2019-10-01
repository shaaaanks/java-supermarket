LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'Buy one get one free','BOGOF     ','','\0',NULL,'\0',NULL),(2,'Three for the price of two on selected sandwich fillers','3FTPO2    ','\0','',1,'\0',NULL),(3,'Three for the price of two on selected milk based products','3FTPO2    ','\0','',2,'\0',NULL),(4,'Discount of 10%','Discount  ','\0','\0',NULL,'',10),(5,'Discount 0f 20%','Discount  ','\0','\0',NULL,'',20);
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `productoffers`
--

LOCK TABLES `productoffers` WRITE;
/*!40000 ALTER TABLE `productoffers` DISABLE KEYS */;
INSERT INTO `productoffers` VALUES (1,1),(2,3),(2,4),(2,12),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),(4,11);
/*!40000 ALTER TABLE `productoffers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Beans',0.5600),(2,'Corn Flakes',2.2200),(3,'Cheddar Cheese (250g)',1.9900),(4,'Ham (5 slices)',2.4900),(5,'Milk (2 litre Semi Skimmed)',2.1000),(6,'Milk (1 litre Semi Skimmed)',1.2000),(7,'Milk (0.5 litre Semi Skimmed)',0.6500),(8,'Milk ( 2litre Skimmed)',2.1000),(9,'Milk (1 litre Skimmed)',1.2000),(10,'Milk (0.5 litre Skimmed)',0.6500),(11,'Margerine (500g)',2.1200),(12,'Butter (500g)',2.3400),(13,'Eggs (Pack of 6)',1.5900),(14,'Toothpaste',1.0000);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;