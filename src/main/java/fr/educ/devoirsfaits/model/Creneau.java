package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="creneau")
public class Creneau implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long idCreneau;
    long dateDebut;
    long dateFin;

    @OneToOne
    @JoinColumn(name="id_salle")
    private Salle salle;

    @ManyToMany
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Where(clause = "role = 'Eleve'")
    List<Eleve> eleves = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Where(clause = "role = 'Professeur'")
    List<Professeur> professeurs = new ArrayList<>();


    public Creneau() { }

    public Creneau(long dateDebut, long dateFin, Salle salle) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.salle = salle;
    }

    public long getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(long idCreneau) {
        this.idCreneau = idCreneau;
    }

    public long getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(long dateDebut) {
        this.dateDebut = dateDebut;
    }

    public long getDateFin() {
        return dateFin;
    }

    public void setDateFin(long dateFin) {
        this.dateFin = dateFin;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }


    public void setEleves(List<Eleve> eleves)  {
        this.eleves = eleves;
    }


    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }
}
