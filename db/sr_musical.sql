CREATE DATABASE  IF NOT EXISTS `sr_musical` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sr_musical`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: sr_musical
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.21-MariaDB

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
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artista` varchar(100) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `letra` varchar(5000) NOT NULL,
  `api` varchar(10) NOT NULL,
  `genero` varchar(20) DEFAULT NULL,
  `numero_visualizacion` int(11) DEFAULT NULL,
  `valoracion` int(1) DEFAULT NULL,
  `duracion_segundo` int(5) DEFAULT NULL,
  `estado_api_sentiment` int(1) DEFAULT NULL,
  `estado_api_sentiment140` int(1) DEFAULT NULL,
  `estado_api_repustate` int(1) DEFAULT NULL,
  `estado_api_text_processing` int(1) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FK_cancion_estado_api_sentiment_id` (`estado_api_sentiment`),
  KEY `FK_cancion_estado_api_sentiment140_id` (`estado_api_sentiment140`),
  KEY `FK_cancion_estado_api_text_processing_id` (`estado_api_text_processing`),
  KEY `FK_cancion_estado_api_repustate_id` (`estado_api_repustate`),
  KEY `FK_cancion_valoracion_id` (`valoracion`),
  CONSTRAINT `FK_cancion_estado_api_repustate_id` FOREIGN KEY (`estado_api_repustate`) REFERENCES `estado` (`id`),
  CONSTRAINT `FK_cancion_estado_api_sentiment140_id` FOREIGN KEY (`estado_api_sentiment140`) REFERENCES `estado` (`id`),
  CONSTRAINT `FK_cancion_estado_api_sentiment_id` FOREIGN KEY (`estado_api_sentiment`) REFERENCES `estado` (`id`),
  CONSTRAINT `FK_cancion_estado_api_text_processing_id` FOREIGN KEY (`estado_api_text_processing`) REFERENCES `estado` (`id`),
  CONSTRAINT `FK_cancion_valoracion_id` FOREIGN KEY (`valoracion`) REFERENCES `valoracion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancion_usuario_valoracion`
--

DROP TABLE IF EXISTS `cancion_usuario_valoracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion_usuario_valoracion` (
  `cancion` int(11) NOT NULL,
  `usuario` int(11) NOT NULL,
  `valoracion` int(1) DEFAULT NULL,
  PRIMARY KEY (`cancion`,`usuario`),
  KEY `FK_cancion_usuario_valoracion_usuario_id` (`usuario`),
  KEY `FK_cancion_usuario_valoracion_valoracion_id` (`valoracion`),
  CONSTRAINT `FK_cancion_usuario_valoracion_cancion_id` FOREIGN KEY (`cancion`) REFERENCES `cancion` (`id`),
  CONSTRAINT `FK_cancion_usuario_valoracion_usuario_id` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_cancion_usuario_valoracion_valoracion_id` FOREIGN KEY (`valoracion`) REFERENCES `valoracion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion_usuario_valoracion`
--

LOCK TABLES `cancion_usuario_valoracion` WRITE;
/*!40000 ALTER TABLE `cancion_usuario_valoracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancion_usuario_valoracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL,
  `habilitado` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'positiva',''),(2,'neutral',''),(3,'negativa','');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuenta` varchar(50) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `ocupacion` varchar(20) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `numero_celular` bigint(10) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `habilitado` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoracion`
--

DROP TABLE IF EXISTS `valoracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valoracion` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `habilitado` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoracion`
--

LOCK TABLES `valoracion` WRITE;
/*!40000 ALTER TABLE `valoracion` DISABLE KEYS */;
INSERT INTO `valoracion` VALUES (1,'Muy Alto',''),(2,'Alto',''),(3,'Medio',''),(4,'Bajo',''),(5,'Muy Bajo','');
/*!40000 ALTER TABLE `valoracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sr_musical'
--

--
-- Dumping routines for database 'sr_musical'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-29 18:21:03
