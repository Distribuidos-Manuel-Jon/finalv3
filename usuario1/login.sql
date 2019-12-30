# Create DB
CREATE DATABASE IF NOT EXISTS `login` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `login`;


--
-- Table structure for table `cliente`
--
SET FOREIGN_KEY_CHECKS=0; 
DROP TABLE IF EXISTS `login`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = latin1*/;

CREATE TABLE `login` (
  
	`usuario` varchar(30) NOT NULL,
  
	`contrasena` varchar(60) NOT NULL,
  
  
	PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `login`.`login` (`usuario`, `contrasena`) VALUES ('usuario1', '1234');

INSERT INTO `login`.`login` (`usuario`, `contrasena`) VALUES ('usuario2', '1234');

SET FOREIGN_KEY_CHECKS=1;
# Add Data List