package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateur {

    @Id
    int ID_user;
    String password;
    String nom;
    String Prenom;
    String Mail;
    boolean Disponible;
    boolean Professeur;
    String Telephone;
    String classe;
    boolean Actif;

    int ID_etablissement;


        public Utilisateur(String password, String nom, String prenom, String mail, boolean disponible, boolean professeur, String telephone, String classe, boolean actif, int ID_etablissement) {
            this.password = password;
            this.nom = nom;
            Prenom = prenom;
            Mail = mail;
            Disponible = disponible;
            Professeur = professeur;
            Telephone = telephone;
            this.classe = classe;
            Actif = actif;
            this.ID_etablissement = ID_etablissement;
        }


        public Utilisateur() {
        }


        public int getID_user() {
            return ID_user;
        }

        public void setID_user(int ID_user) {
            this.ID_user = ID_user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return Prenom;
        }

        public void setPrenom(String prenom) {
            Prenom = prenom;
        }

        public String getMail() {
            return Mail;
        }

        public void setMail(String mail) {
            Mail = mail;
        }

        public boolean isDisponible() {
            return Disponible;
        }

        public void setDisponible(boolean disponible) {
            Disponible = disponible;
        }

        public boolean isProfesseur() {
            return Professeur;
        }

        public void setProfesseur(boolean professeur) {
            Professeur = professeur;
        }

        public String getTelephone() {
            return Telephone;
        }

        public void setTelephone(String telephone) {
            Telephone = telephone;
        }

        public String getClasse() {
            return classe;
        }

        public void setClasse(String classe) {
            this.classe = classe;
        }

        public boolean isActif() {
            return Actif;
        }

        public void setActif(boolean actif) {
            Actif = actif;
        }

        public int getID_etablissement() {
            return ID_etablissement;
        }

        public void setID_etablissement(int ID_etablissement) {
            this.ID_etablissement = ID_etablissement;
        }
}
