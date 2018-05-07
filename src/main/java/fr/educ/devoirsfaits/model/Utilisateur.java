package fr.educ.devoirsfaits.model;

import fr.educ.devoirsfaits.service.Crypter;

import javax.inject.Inject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue
    long idUtilisateur;

    @Column(nullable = false)
    String nom;

    @Column(nullable = false)
    String prenom;

    @Column(unique = true, nullable = false)
    String mail;

    @Column(nullable = false)
    String password;
    String telephone;
    String classe;
    boolean disponible;
    boolean professeur;
    boolean administrateur;
    boolean actif;

    //@OneToOne(optional = false)
    @OneToOne
    private Etablissement etablissement ;

    @OneToMany(mappedBy="eleve")
    private Collection<Message> suivi ;

    @OneToMany(mappedBy="redacteur")
    private Collection<Message> messages ;

    @ManyToMany
    private Collection<Groupe> groupes ;

    @Transient
    @Inject
    private Crypter crypt;


    public Utilisateur(String nom, String prenom, String mail, String telephone, String password, String classe, boolean disponible, boolean professeur, boolean administrateur, boolean actif, Etablissement etablissement, Collection<Message> suivi, Collection<Message> messages, Collection<Groupe> groupes) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.password = crypt.crypt(password);
        this.classe = classe;
        this.disponible = disponible;
        this.professeur = professeur;
        this.administrateur = administrateur;
        this.actif = actif;
        this.etablissement = etablissement;
        this.suivi = suivi;
        this.messages = messages;
        this.groupes = groupes;
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

    public Collection<Message> getSuivi() {
        return suivi;
    }

    public void setSuivi(Collection<Message> suivi) {
        this.suivi = suivi;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Collection<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Collection<Groupe> groupes) {
        this.groupes = groupes;
    }
}
