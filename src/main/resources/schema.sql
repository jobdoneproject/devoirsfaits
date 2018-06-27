-- MySQL dump 10.13  Distrib 8.0.11, for Linux (x86_64)
--
-- Host: localhost    Database: devoirsfaits
-- ------------------------------------------------------
-- Server version	8.0.11
CREATE DATABASE `devoirsfaits` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `etablissement`
--
DROP TABLE IF EXISTS `relations_groupes`;
DROP TABLE IF EXISTS `reference`;
DROP TABLE IF EXISTS `participant`;
DROP TABLE IF EXISTS `message`;
DROP TABLE IF EXISTS `groupe`;
DROP TABLE IF EXISTS `creneau`;
DROP TABLE IF EXISTS `utilisateur`;
DROP TABLE IF EXISTS `salle`;
DROP TABLE IF EXISTS `etablissement`;


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `etablissement` (
  `id_etablissement` int(11) NOT NULL AUTO_INCREMENT,
  `nom_etablissement` varchar(255) DEFAULT NULL,
  `url_etablissement` varchar(255) DEFAULT NULL,
  `ville_etablissement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_etablissement`),
  UNIQUE KEY `url_etablissement` (`url_etablissement`),
  UNIQUE KEY `UKkvwauxym4qw10s3ivfe7hk6u2` (`ville_etablissement`,`nom_etablissement`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `salle`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `salle` (
  `id_salle` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `id_etablissement` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_salle`),
  KEY `FK_Salle_id_etablissement` (`id_etablissement`),
  CONSTRAINT `FK_Salle_id_etablissement` FOREIGN KEY (`id_etablissement`) REFERENCES `etablissement` (`id_etablissement`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `utilisateur`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `password` char(40) DEFAULT NULL,
  `nom` varchar(50) NOT NULL DEFAULT 'nom',
  `prenom` varchar(50) NOT NULL DEFAULT 'prenom',
  `mail` varchar(100) NOT NULL DEFAULT 'mail@mail.fr',
  `disponible` tinyint(1) NOT NULL DEFAULT '0',
  `actif` tinyint(1) NOT NULL DEFAULT '0',
  `telephone` varchar(25) DEFAULT NULL,
  `classe` varchar(25) DEFAULT NULL,
  `id_etablissement` int(11) NOT NULL DEFAULT '1',
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `mail` (`mail`),
  KEY `FK_Utilisateur_id_etablissement` (`id_etablissement`),
  CONSTRAINT `FK_Utilisateur_id_etablissement` FOREIGN KEY (`id_etablissement`) REFERENCES `etablissement` (`id_etablissement`)
) ENGINE=InnoDB AUTO_INCREMENT=1225 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `creneau`
--



/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `creneau` (
  `id_creneau` int(11) NOT NULL AUTO_INCREMENT,
  `date_debut` int(11) DEFAULT NULL,
  `date_fin` int(11) DEFAULT NULL,
  `id_salle` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_creneau`),
  KEY `FK_Creneau_id_salle` (`id_salle`),
  CONSTRAINT `FK_Creneau_id_salle` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=384 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `groupe`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `groupe` (
  `id_groupe` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `message`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` text,
  `id_eleve` bigint(255) NOT NULL,
  `date_message` int(11) DEFAULT NULL,
  `id_utilisateur` bigint(255) NOT NULL,
  PRIMARY KEY (`id_message`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `participant`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `participant` (
  `present` tinyint(1) NOT NULL,
  `id_creneau` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_creneau`,`id_utilisateur`),
  KEY `FK_Participant_id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `FK_Participant_id_creneau` FOREIGN KEY (`id_creneau`) REFERENCES `creneau` (`id_creneau`),
  CONSTRAINT `FK_Participant_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `reference`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reference` (
  `id_creneau` int(11) NOT NULL,
  `id_message` int(11) NOT NULL,
  PRIMARY KEY (`id_creneau`,`id_message`),
  KEY `FK_Reference_id_message` (`id_message`),
  CONSTRAINT `FK_Reference_id_creneau` FOREIGN KEY (`id_creneau`) REFERENCES `creneau` (`id_creneau`),
  CONSTRAINT `FK_Reference_id_message` FOREIGN KEY (`id_message`) REFERENCES `message` (`id_message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `relations_groupes`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `relations_groupes` (
  `id_utilisateur` int(11) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  PRIMARY KEY (`id_utilisateur`,`id_groupe`),
  KEY `FK_RelationsGroupes_id_groupe` (`id_groupe`),
  CONSTRAINT `FK_RelationsGroupes_id_groupe` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id_groupe`),
  CONSTRAINT `FK_RelationsGroupes_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
