package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Salle {

    @Id
    @GeneratedValue
    long idSalle;
    String nom;
    long idEtablissement;

    public Salle(String nom, long idEtablissement) {
        this.nom = nom;
        this.idEtablissement = idEtablissement;
    }

    public Salle() {
    }

    public long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(long idSalle) {
        this.idSalle = idSalle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(long idEtablissement) {
        this.idEtablissement = idEtablissement;
    }
}
