# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Hôte: 127.0.0.1 (MySQL 5.6.25)
# Base de données: devoirsfaits
# Temps de génération: 2018-05-15 13:21:13 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table creneau
# ------------------------------------------------------------

DROP TABLE IF EXISTS `creneau`;

CREATE TABLE `creneau` (
  `id_creneau` int(11) NOT NULL AUTO_INCREMENT,
  `date_creneau` date DEFAULT NULL,
  `duree` int(11) DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `id_salle` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_creneau`),
  KEY `FK_Creneau_id_salle` (`id_salle`),
  CONSTRAINT `FK_Creneau_id_salle` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table etablissement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `etablissement`;

CREATE TABLE `etablissement` (
  `id_etablissement` int(11) NOT NULL AUTO_INCREMENT,
  `nom_etablissement` varchar(255) DEFAULT NULL,
  `url_etablissement` varchar(255) DEFAULT NULL,
  `ville_etablissement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_etablissement`),
  UNIQUE KEY `url_etablissement` (`url_etablissement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Affichage de la table groupe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `groupe`;

CREATE TABLE `groupe` (
  `id_groupe` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` text,
  `id_eleve` int(11) DEFAULT NULL,
  `date_message` date DEFAULT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_message`),
  KEY `FK_Message_id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `FK_Message_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table participant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `participant`;

CREATE TABLE `participant` (
  `present` tinyint(1) NOT NULL,
  `id_creneau` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_creneau`,`id_utilisateur`),
  KEY `FK_Participant_id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `FK_Participant_id_creneau` FOREIGN KEY (`id_creneau`) REFERENCES `creneau` (`id_creneau`),
  CONSTRAINT `FK_Participant_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table reference
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reference`;

CREATE TABLE `reference` (
  `id_creneau` int(11) NOT NULL,
  `id_message` int(11) NOT NULL,
  PRIMARY KEY (`id_creneau`,`id_message`),
  KEY `FK_Reference_id_message` (`id_message`),
  CONSTRAINT `FK_Reference_id_creneau` FOREIGN KEY (`id_creneau`) REFERENCES `creneau` (`id_creneau`),
  CONSTRAINT `FK_Reference_id_message` FOREIGN KEY (`id_message`) REFERENCES `message` (`id_message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table relations_groupes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `relations_groupes`;

CREATE TABLE `relations_groupes` (
  `id_utilisateur` int(11) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  PRIMARY KEY (`id_utilisateur`,`id_groupe`),
  KEY `FK_RelationsGroupes_id_groupe` (`id_groupe`),
  CONSTRAINT `FK_RelationsGroupes_id_groupe` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id_groupe`),
  CONSTRAINT `FK_RelationsGroupes_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table salle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `salle`;

CREATE TABLE `salle` (
  `id_salle` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `id_etablissement` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_salle`),
  KEY `FK_Salle_id_etablissement` (`id_etablissement`),
  CONSTRAINT `FK_Salle_id_etablissement` FOREIGN KEY (`id_etablissement`) REFERENCES `etablissement` (`id_etablissement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table utilisateur
# ------------------------------------------------------------

DROP TABLE IF EXISTS `utilisateur`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
