# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Hôte: localhost (MySQL 5.7.20)
# Base de données: devoirsfaits
# Temps de génération: 2018-05-13 19:56:41 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table Creneau
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Creneau`;

CREATE TABLE `Creneau` (
  `id_creneau` int(11) NOT NULL AUTO_INCREMENT,
  `date_creneau` date DEFAULT NULL,
  `duree` int(11) DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `id_salle` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_creneau`),
  KEY `FK_Creneau_id_salle` (`id_salle`),
  CONSTRAINT `FK_Creneau_id_salle` FOREIGN KEY (`id_salle`) REFERENCES `Salle` (`id_salle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table Etablissement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Etablissement`;

CREATE TABLE `Etablissement` (
  `id_etablissement` int(11) NOT NULL AUTO_INCREMENT,
  `nom_etablissement` varchar(255) DEFAULT NULL,
  `url_etablissement` varchar(255) DEFAULT NULL,
  `ville_etablissement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_etablissement`),
  UNIQUE KEY `url_etablissement` (`url_etablissement`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;



# Affichage de la table Groupe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Groupe`;

CREATE TABLE `Groupe` (
  `id_groupe` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table Message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` text,
  `id_eleve` int(11) DEFAULT NULL,
  `date_message` date DEFAULT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_message`),
  KEY `FK_Message_id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `FK_Message_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table Participant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Participant`;

CREATE TABLE `Participant` (
  `present` tinyint(1) NOT NULL,
  `id_creneau` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_creneau`,`id_utilisateur`),
  KEY `FK_Participant_id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `FK_Participant_id_creneau` FOREIGN KEY (`id_creneau`) REFERENCES `Creneau` (`id_creneau`),
  CONSTRAINT `FK_Participant_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table Reference
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reference`;

CREATE TABLE `Reference` (
  `id_creneau` int(11) NOT NULL,
  `id_message` int(11) NOT NULL,
  PRIMARY KEY (`id_creneau`,`id_message`),
  KEY `FK_Reference_id_message` (`id_message`),
  CONSTRAINT `FK_Reference_id_creneau` FOREIGN KEY (`id_creneau`) REFERENCES `Creneau` (`id_creneau`),
  CONSTRAINT `FK_Reference_id_message` FOREIGN KEY (`id_message`) REFERENCES `Message` (`id_message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table RelationsGroupes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RelationsGroupes`;

CREATE TABLE `RelationsGroupes` (
  `id_utilisateur` int(11) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  PRIMARY KEY (`id_utilisateur`,`id_groupe`),
  KEY `FK_RelationsGroupes_id_groupe` (`id_groupe`),
  CONSTRAINT `FK_RelationsGroupes_id_groupe` FOREIGN KEY (`id_groupe`) REFERENCES `Groupe` (`id_groupe`),
  CONSTRAINT `FK_RelationsGroupes_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table Salle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Salle`;

CREATE TABLE `Salle` (
  `id_salle` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `id_etablissement` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_salle`),
  KEY `FK_Salle_id_etablissement` (`id_etablissement`),
  CONSTRAINT `FK_Salle_id_etablissement` FOREIGN KEY (`id_etablissement`) REFERENCES `Etablissement` (`id_etablissement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table Utilisateur
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Utilisateur`;

CREATE TABLE `Utilisateur` (
  `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(200) DEFAULT NULL,
  `nom` varchar(50) NOT NULL DEFAULT 'nom',
  `prenom` varchar(50) NOT NULL DEFAULT 'prenom',
  `mail` varchar(100) NOT NULL DEFAULT 'mail@mail.fr',
  `disponible` tinyint(1) NOT NULL DEFAULT '0',
  `actif` tinyint(1) NOT NULL DEFAULT '0',
  `telephone` varchar(25) DEFAULT NULL,
  `classe` varchar(25) DEFAULT NULL,
  `id_etablissement` int(11) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `mail` (`mail`),
  KEY `FK_Utilisateur_id_etablissement` (`id_etablissement`),
  CONSTRAINT `FK_Utilisateur_id_etablissement` FOREIGN KEY (`id_etablissement`) REFERENCES `Etablissement` (`id_etablissement`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
