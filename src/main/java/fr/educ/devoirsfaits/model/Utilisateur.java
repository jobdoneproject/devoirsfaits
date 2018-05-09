package fr.educ.devoirsfaits.model;

import fr.educ.devoirsfaits.service.Crypter;

import javax.inject.Inject;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue
    private long idUtilisateur;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;
    private String telephone;
    private String classe;
    boolean disponible;
    boolean actif;
    private boolean professeur;
    private boolean administrateur;
    private long idEtablissement ;

    @Transient
    @Inject
    private Crypter crypt;

    public Utilisateur() { }


    public long getIdUtilisateur() {
        return idUtilisateur;
    }
    /*public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = crypt.crypt(password);
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public boolean isProfesseur() {
        return professeur;
    }

    public void setProfesseur(boolean professeur) {
        this.professeur = professeur;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
