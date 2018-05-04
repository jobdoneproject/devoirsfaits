package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Salle {

    @Id
    @GeneratedValue
    int ID_salle;
    String nom;
    int ID_etablissement;

    public Salle(String nom, int ID_etablissement) {
        this.nom = nom;
        this.ID_etablissement = ID_etablissement;
    }

    public Salle() {
    }

    public int getID_salle() {
        return ID_salle;
    }

    public void setID_salle(int ID_salle) {
        this.ID_salle = ID_salle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getID_etablissement() {
        return ID_etablissement;
    }

    public void setID_etablissement(int ID_etablissement) {
        this.ID_etablissement = ID_etablissement;
    }
}
