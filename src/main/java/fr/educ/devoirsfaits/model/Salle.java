package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Salle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long idSalle;

    String nom;

    @OneToOne
    @JoinColumn(name="id_etablissement")
    @JsonIgnore
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
