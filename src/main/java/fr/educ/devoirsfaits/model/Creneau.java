package fr.educ.devoirsfaits.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Creneau {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long idCreneau;
    long dateDebut;
    long dateFin;

    @OneToOne
    private Salle salle;

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

}
