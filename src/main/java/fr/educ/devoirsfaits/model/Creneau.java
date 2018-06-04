package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.educ.devoirsfaits.json.ParticipantSerializer;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

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

/*    @ManyToMany
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Where(clause = "role = 'ParticipantSerializer'")
    @WhereJoinTable(clause = "present=true")
    List<ParticipantSerializer> presents = new ArrayList<>();

    public List<ParticipantSerializer> getPresents() {
        return presents;
    }

    public void setPresents(List<ParticipantSerializer> eleves)  {
        this.presents = eleves;
    }



    @ManyToMany
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Where(clause = "role = 'ParticipantSerializer'")
    @WhereJoinTable(clause = "present=false")
    List<ParticipantSerializer> absents = new ArrayList<>();

    public List<ParticipantSerializer> getAbsents() {
        return absents;
    }

    public void setAbsents(List<ParticipantSerializer> eleves)  {
        this.absents = eleves;
    }*/

    @ElementCollection
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")}
    )
    @MapKeyJoinColumn(name = "id_utilisateur", table = "participant")
    @MapKeyClass(Utilisateur.class)
    @Column(name="present")
    @JsonSerialize(using = ParticipantSerializer.class)
    Map<Utilisateur,Boolean> participants = new HashMap<>();
    public Map<Utilisateur,Boolean> getParticipants() {
        return participants;
    }

    public void setParticipants(Map<Utilisateur,Boolean> participants) {
        this.participants = participants;
    }


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


/*

    @ManyToMany
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Where(clause = "role = 'Professeur'")
    Collection<Professeur> professeurs = new ArrayList<>();

    public Collection<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(Collection<Professeur> professeurs) {
        this.professeurs = professeurs;
    }
*/



}
