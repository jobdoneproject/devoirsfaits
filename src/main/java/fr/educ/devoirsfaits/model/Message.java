package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idMessage;

    private String contenu;

    private Utilisateur eleve;

    private Date dateMessage;

    private Utilisateur redacteur;

    public Message(String contenu, Utilisateur eleve, Date dateMessage, Utilisateur redacteur) {
        this.contenu = contenu;
        this.eleve = eleve;
        this.dateMessage = dateMessage;
        this.redacteur = redacteur;
    }

    public Message() { }

    public long getIdMessage() {
        return idMessage;
    }

    /*public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }*/

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Utilisateur getEleve() {
        return eleve;
    }

    public void setEleve(Utilisateur eleve) {
        this.eleve = eleve;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public Utilisateur getRedacteur() {
        return redacteur;
    }

    public void setRedacteur(Utilisateur redacteur) {
        this.redacteur = redacteur;
    }
}
