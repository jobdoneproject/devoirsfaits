package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Creneau {

    @Id
    @GeneratedValue
    long idCreneau;
    String dateCreneau;
    int duree;
    String heureDebut;
    String heureFin;
    long idSalle;

    public Creneau(String dateCreneau, int duree, String heureDebut, String heureFin, long idSalle) {
        this.dateCreneau = dateCreneau;
        this.duree = duree;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idSalle = idSalle;
    }

    public Creneau() {
    }

    public long getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(long idCreneau) {
        this.idCreneau = idCreneau;
    }

    public String getDateCreneau() {
        return dateCreneau;
    }

    public void setDateCreneau(String dateCreneau) {
        this.dateCreneau = dateCreneau;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(long idSalle) {
        this.idSalle = idSalle;
    }
}
