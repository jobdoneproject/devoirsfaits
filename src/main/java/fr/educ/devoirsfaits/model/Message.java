package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.educ.devoirsfaits.json.MessageDeserializer;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@JsonDeserialize(using = MessageDeserializer.class)
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private long idMessage;

    private String contenu;


    @OneToOne
    @JoinColumn(name="id_Eleve")
    //@JsonIgnore
    private Eleve eleve;

    private long dateMessage;

    @OneToOne
    @JoinColumn(name="id_Utilisateur")
    //@JsonIgnore
    private Utilisateur redacteur;

    public Message() {
    }

    public Message(String contenu, Eleve eleve, long dateMessage, Utilisateur redacteur) {
        this.contenu = contenu;
        this.eleve = eleve;
        this.dateMessage = dateMessage;
        this.redacteur = redacteur;
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

    public long getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(long dateMessage) {
        this.dateMessage = dateMessage;
    }

    public Utilisateur getRedacteur() {
        return redacteur;
    }

    public void setRedacteur(Utilisateur redacteur) {
        this.redacteur = redacteur;
    }
}
