Le port 8080 est injecté sur stack Java !


#MySql  

Create the database if not exist :

#### CREATE DATABASE `devoirsfaits` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

Lancer les scripts de staging pour le dev et le staging.

Deployement sur cleverCloud avec :
une stack pour le staging en 1 Add-on et deux applications de services : 
0-MySql Add-on (devoirsfaits-preprod)
--> pour le service MySQL de staging/preprod
Credentials
Host 	bz6hugps9-mysql.services.clever-cloud.com
Database 	bz6hugps9
User 	utfn7whmv6bbdbnw
Port 	3306
Password 	Cr70EGqc66wyZlzFjJS

1-Application du type Javan+Maven (devoirsfaits-maven-staging) sur le git :  jobdoneprojects/devoirfaits.git
--> qui génère le back et le WAR
2-Application du type Java+JAR+MySqlAddon (devoirsfaits-staging) sur le git : jobdoneprojects/devoirfaitsfront.git
--> pour le front et le static HTML/CSS
--> Alias de nom de domaine : http://devoirsfaits-demo.cleverapps.io/
--> Services liées : 
    Addon : devoirsfaits-preprod
    Application : devoirsfaits-maven-staging

TODO : 
Une stack de production
Versioning
Script de migration