package fr.educ.devoirsfaits.model;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.security.MessageDigest;

@Entity
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue
    long idUtilisateur;
    String password;

    @Column(nullable = false)
    String nom;

    @Column(nullable = false)
    String prenom;

    @Column(nullable = false)
    String mail;
    boolean disponible;
    boolean professeur;
    boolean administrateur;
    String telephone;
    String classe;
    boolean actif;

    @OneToOne
    private Etablissement etablissement ;

    @OneToMany(mappedBy="eleve")
    private Collection<Message> suivi ;

    @OneToMany(mappedBy="redacteur")
    private Collection<Message> messages ;

    public Utilisateur(String password, String nom, String prenom, String mail, boolean disponible, boolean professeur, boolean administrateur, String telephone, String classe, boolean actif, Etablissement etablissement) {
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
        this.etablissement = etablissement;
    }

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

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
            this.etablissement = etablissement;
        }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

}
