
LOCK TABLES `etablissement` WRITE;
/*!40000 ALTER TABLE `etablissement` DISABLE KEYS */;

INSERT INTO `etablissement` (`id_etablissement`, `nom_etablissement`, `url_etablissement`, `ville_etablissement`)
VALUES
	(1,'Poudlard','ecosse-poudlard','Ecosse'),
	(2,'Salsepareille','schtroumpf-ville-salsepareille','Schtroumpf Ville'),
	(3,'Hobbit School','sous-colline-hobbit-school','Sous Colline');

/*!40000 ALTER TABLE `etablissement` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;

INSERT INTO `utilisateur` (`id_utilisateur`, `password`, `nom`, `prenom`, `mail`, `disponible`, `actif`, `telephone`, `classe`, `id_etablissement`, `role`)
VALUES
	(1,'202cb962ac59075b964b07152d234b70','Albus','Dumbeldore','a.dumbuldore@poudlard.com',1,1,'',NULL,1,'Administrateur'),
	(2,'202cb962ac59075b964b07152d234b70','Grand','Schtroumpf','Schtroumpf@Schtroumpf.com',1,1,'',NULL,2,'Administrateur'),
	(3,'202cb962ac59075b964b07152d234b70','LeGris','Gandalph','gris@magicien.io.org',1,1,'',NULL,3,'Administrateur'),
	(4,'202cb962ac59075b964b07152d234b70','Londubat','Neville','Neville@poudlard.com',1,1,'',NULL,1,'Professeur'),
	(5,'202cb962ac59075b964b07152d234b70','Rubeus','Hagrid','Rubeus@poudlard.com',1,1,'',NULL,1,'Professeur'),
	(6,'202cb962ac59075b964b07152d234b70','Vector','Septima','Septima@poudlard.com',1,1,'',NULL,1,'Professeur'),
	(7,'202cb962ac59075b964b07152d234b70','ALunette','Schtroumpf','ALunette@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(8,'202cb962ac59075b964b07152d234b70','Schtroumpfette','Schtroumpf','Schtroumpfette@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(9,'202cb962ac59075b964b07152d234b70','Causto','Schtroumpf','Causto@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(10,'202cb962ac59075b964b07152d234b70','Bricoleur','Schtroumpf','bricolleur@Schtroumpf.com',1,1,'',NULL,2,'Professeur'),
	(11,'202cb962ac59075b964b07152d234b70','Baggins','Bilbo','b.baggins@hobbit.org',1,1,'',NULL,3,'Professeur'),
	(12,'202cb962ac59075b964b07152d234b70','Potter','Harry','Potter@poudlard.com',1,1,'',NULL,1,'Eleve'),
	(13,'202cb962ac59075b964b07152d234b70','Malefoy','Drago','Malefoy@poudlard.com',1,1,'',NULL,1,'Eleve'),
	(14,'202cb962ac59075b964b07152d234b70','Ombrage','Dolores','Ombrage@poudlard.com',1,1,'',NULL,1,'Eleve'),
	(15,'202cb962ac59075b964b07152d234b70','Weasley','Ronald','Weasley@poudlard.com',1,1,'',NULL,1,'Eleve'),
	(16,'202cb962ac59075b964b07152d234b70','Granger','Hermione','Granger@poudlard.com',1,1,'',NULL,1,'Eleve'),
	(17,'202cb962ac59075b964b07152d234b70','Jedusor','Tom','Jedusor@poudlard.com',1,1,'',NULL,1,'Eleve'),
	(18,'202cb962ac59075b964b07152d234b70','Schtroumpf','Farceur','Farceur@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(19,'202cb962ac59075b964b07152d234b70','Schtroumpf','grognon','grognon@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(20,'202cb962ac59075b964b07152d234b70','Schtroumpf','raleur','raleur@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(21,'202cb962ac59075b964b07152d234b70','Schtroumpf','timide','timide@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(23,'202cb962ac59075b964b07152d234b70','Schtroumpf','paysan','paysan@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(24,'202cb962ac59075b964b07152d234b70','Schtroumpf','peureux','peureux@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(25,'202cb962ac59075b964b07152d234b70','Schtroumpf','poete','poete@Schtroumpf.com',1,1,'',NULL,2,'Eleve'),
	(26,'202cb962ac59075b964b07152d234b70','Gamgie','Sam','Gamgie@hobbit.org',1,1,'',NULL,3,'Eleve'),
	(27,'202cb962ac59075b964b07152d234b70','Bessac','Frodo','Bessac@hobbit.org',1,1,'',NULL,3,'Eleve'),
	(28,'202cb962ac59075b964b07152d234b70','Brandibouc','Merry','Brandibouc@hobbit.org',1,1,'',NULL,3,'Eleve');

/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
