DROP TABLE IF EXISTS `offers`;
CREATE TABLE `offers` (
  `OfferID` int(11) NOT NULL AUTO_INCREMENT,
  `OfferDescription` varchar(60) NOT NULL,
  `ShortDescription` varchar(10) DEFAULT NULL,
  `BOGOF` bit(1) NOT NULL,
  `TFTPOT` bit(1) NOT NULL,
  `TFTPOTGroup` int(11) DEFAULT NULL,
  `DISCOUNT` bit(1) NOT NULL,
  `DiscountPercentage` int(11) DEFAULT NULL,
  PRIMARY KEY (`OfferID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `productoffers`;
CREATE TABLE `productoffers` (
  `OfferID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  PRIMARY KEY (`OfferID`,`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(40) NOT NULL,
  `UnitPrice` decimal(29,4) NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `ProductID_UNIQUE` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;