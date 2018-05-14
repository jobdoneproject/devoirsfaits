LOCK TABLES `Etablissement` WRITE;
/*!40000 ALTER TABLE `Etablissement` DISABLE KEYS */;



INSERT INTO `Etablissement` (`id_etablissement`, `nom_etablissement`, `url_etablissement`, `ville_etablissement`)
VALUES
	(1,'Poudlard','ecosse-poudlard','Ecosse'),
	(2,'Salsepareille','schtroumpf-ville-salsepareille','Schtroumpf Ville'),
	(3,'Hobbit School','sous-colline-hobbit-school','Sous Colline');

/*!40000 ALTER TABLE `Etablissement` ENABLE KEYS */;
UNLOCK TABLES;


	
LOCK TABLES `Utilisateur` WRITE;
/*!40000 ALTER TABLE `Utilisateur` DISABLE KEYS */;


INSERT INTO `Utilisateur` (`id_utilisateur`, `password`, `nom`, `prenom`, `mail`, `disponible`, `actif`, `telephone`, `classe`, `id_etablissement`, `role`)
VALUES
	(1,'81dc9bdb52d04dc20036dbd8313ed055','Albus','Dumbeldore','a.dumbuldore@poudlard.com',1,1,'',NULL,1,'Administrateur'),
	(2,'81dc9bdb52d04dc20036dbd8313ed055','Grand','Schtroumpf','Schtroumpf@Schtroumpf.com',1,1,'',NULL,2,'Administrateur'),
	(3,'81dc9bdb52d04dc20036dbd8313ed055','LeGris','Gandalph','gris@magicien.io.org',1,1,'',NULL,3,'Administrateur'),
	(4,'81dc9bdb52d04dc20036dbd8313ed055','Londubat','Neville','Neville@poudlard.com',1,1,'',NULL,1,'Professeur'),
	(5,'81dc9bdb52d04dc20036dbd8313ed055','Rubeus','Hagrid','Rubeus@poudlard.com',1,1,'',NULL,1,'Professeur'),
	(6,'81dc9bdb52d04dc20036dbd8313ed055','Vector','Septima','Septima@poudlard.com',1,1,'',NULL,1,'Professeur'),
	(7,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','Alunettes','ALunette@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(8,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','Schtroumpfette','Schtroumpfette@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(9,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','Causto','Causto@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(10,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','Bricoleur','bricolleur@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(11,'81dc9bdb52d04dc20036dbd8313ed055','Baggins','Bilbo','b.baggins@hobbit.org',1,1,'',NULL,3,'Professeur'),
	(12,'81dc9bdb52d04dc20036dbd8313ed055','Potter','Harry','Potter@poudlard.com',1,1,'','Gryffondor',1,'Eleve'),
	(13,'81dc9bdb52d04dc20036dbd8313ed055','Malefoy','Drago','Malefoy@poudlard.com',1,1,'','Serpentard',1,'Eleve'),
	(14,'81dc9bdb52d04dc20036dbd8313ed055','Ombrage','Dolores','Ombrage@poudlard.com',1,1,'','Serpentard',1,'Eleve'),
	(15,'81dc9bdb52d04dc20036dbd8313ed055','Weasley','Ronald','Weasley@poudlard.com',1,1,'','Gryffondor',1,'Eleve'),
	(16,'81dc9bdb52d04dc20036dbd8313ed055','Granger','Hermione','Granger@poudlard.com',1,1,'','Gryffondor',1,'Eleve'),
	(17,'81dc9bdb52d04dc20036dbd8313ed055','Jedusor','Tom','Jedusor@poudlard.com',1,1,'','Serpentard',1,'Eleve'),
	(18,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','Farceur','Farceur@Schtroumpf.com',1,1,'','Schtroumpf2',2,'Eleve'),
	(19,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','grognon','grognon@Schtroumpf.com',1,1,'','Schtroumpf2',2,'Eleve'),
	(20,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','raleur','raleur@Schtroumpf.com',1,1,'','Schtroumpf2',2,'Eleve'),
	(21,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','timide','timide@Schtroumpf.com',1,1,'','Schtroumpf2',2,'Eleve'),
	(23,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','paysan','paysan@Schtroumpf.com',1,1,'','Schtroumpf1',2,'Eleve'),
	(24,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','peureux','peureux@Schtroumpf.com',1,1,'','Schtroumpf1',2,'Eleve'),
	(25,'81dc9bdb52d04dc20036dbd8313ed055','Schtroumpf','poete','poete@Schtroumpf.com',1,1,'','Schtroumpf1',2,'Eleve'),
	(26,'81dc9bdb52d04dc20036dbd8313ed055','Gamgie','Sam','Gamgie@hobbit.org',1,1,'','Petit',3,'Eleve'),
	(27,'81dc9bdb52d04dc20036dbd8313ed055','Bessac','Frodo','Bessac@hobbit.org',1,1,'','Petit',3,'Eleve'),
	(28,'81dc9bdb52d04dc20036dbd8313ed055','Brandibouc','Merry','Brandibouc@hobbit.org',1,1,'','Petit',3,'Eleve');
	

/*!40000 ALTER TABLE `Utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
