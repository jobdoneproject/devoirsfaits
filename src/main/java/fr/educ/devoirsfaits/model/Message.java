package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idMessage;

    private String contenu;


    @OneToOne
    @JoinColumn(name="id_Eleve")
    //@JsonIgnore
    private Eleve eleve;

    private Date dateMessage;

    @OneToOne
    @JoinColumn(name="id_Utilisateur")
    //@JsonIgnore
    private Utilisateur utilisateur;

    public Message() {
    }

    public Message(String contenu, Eleve eleve, Date dateMessage, Utilisateur utilisateur) {
        this.contenu = contenu;
        this.eleve = eleve;
        this.dateMessage = dateMessage;
        //this.utilisateur = utilisateur;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
