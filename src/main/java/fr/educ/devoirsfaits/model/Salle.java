package fr.educ.devoirsfaits.model;

import javax.persistence.*;

@Entity
public class Salle {

    @Id
    @GeneratedValue
    long idSalle;

    String nom;

    @OneToOne
    private Etablissement etablissement ;

    public Salle(String nom, Etablissement etablissement) {
        this.nom = nom;
        this.etablissement = etablissement;
    }

    public Salle() { }

    public long getIdSalle() {
        return idSalle;
    }

    /*public void setIdSalle(long idSalle) {
        this.idSalle = idSalle;
    }*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
}
