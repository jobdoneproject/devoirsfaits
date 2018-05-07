package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.util.Date;

@Entity
public class Creneau {

    @Id
    @GeneratedValue
    long idCreneau;
    Date dateCreneau;
    Time heureDebut;
    Time heureFin;

    @OneToOne
    private Salle salle;

    public Creneau(Date dateCreneau, Time heureDebut, Time heureFin, Salle salle) {
        this.dateCreneau = dateCreneau;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
    }

    public Creneau() { }

    public long getIdCreneau() {
        return idCreneau;
    }

    /*public void setIdCreneau(long idCreneau) {
        this.idCreneau = idCreneau;
    }*/

    public Date getDateCreneau() {
        return dateCreneau;
    }

    public void setDateCreneau(Date dateCreneau) {
        this.dateCreneau = dateCreneau;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
