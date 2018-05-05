package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue
    long idUtilisateur;
    String password;
    String nom;
    String prenom;
    String mail;
    boolean disponible = false;
    boolean professeur = false;
    boolean administrateur = false;
    String telephone;
    String classe;
    boolean actif = false;

    long idEtablissement;


    public Utilisateur(String password, String nom, String prenom, String mail, boolean disponible, boolean professeur, boolean administrateur, String telephone, String classe, boolean actif, long idEtablissement) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.disponible = disponible;
        this.professeur = professeur;
        this.administrateur = administrateur;
        this.telephone = telephone;
        this.classe = classe;
        this.actif = actif;
        this.idEtablissement = idEtablissement;
    }

    public Utilisateur() {
        }


    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isProfesseur() {
        return professeur;
    }

    public void setProfesseur(boolean professeur) {
        this.professeur = professeur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public long getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(long idEtablissement) {
            this.idEtablissement = idEtablissement;
        }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }
}
