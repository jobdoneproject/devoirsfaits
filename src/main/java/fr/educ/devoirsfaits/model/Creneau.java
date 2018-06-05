package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.educ.devoirsfaits.json.CreneauDeserializer;
import fr.educ.devoirsfaits.json.ParticipantSerializer;
import org.hibernate.annotations.Where;

import javax.enterprise.inject.Produces;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="creneau")
@JsonDeserialize(using = CreneauDeserializer.class)
public class Creneau implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long idCreneau;
    long dateDebut;
    long dateFin;

    @OneToOne
    @JoinColumn(name="id_salle")
    private Salle salle;

    @ElementCollection
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")}
    )
    @MapKeyJoinColumn(name = "id_utilisateur", table = "participant")
    @MapKeyClass(Utilisateur.class)
    @Column(name="present")
    @JsonIgnore //on serialize les eleves et les professeurs indépendamment par le biais de getElevesParticipant et getProgesseurs)
    Map<Utilisateur,Boolean> participantsWithPresence = new HashMap<>();


    public Creneau() { }

    public Creneau(long dateDebut, long dateFin, Salle salle) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.salle = salle;
    }

    @JsonSerialize(using = ParticipantSerializer.class)
    @JsonProperty("eleves")
    public Map<Eleve,Boolean> getElevesParticipant() {
        Map<Eleve,Boolean> elevesParticipant = new HashMap<>();
        for (Map.Entry<Utilisateur, Boolean> p : participantsWithPresence.entrySet()) {
            if (p.getKey() instanceof  Eleve) {
                elevesParticipant.put((Eleve) p.getKey(), p.getValue());
            }
        }
        return elevesParticipant;
    }

    public void setElevesParticipant(Map<Eleve,Boolean> elevesParticipant) {
        if (this.participantsWithPresence == null) {
            this.participantsWithPresence = new HashMap<>();
        }
        this.participantsWithPresence.putAll(elevesParticipant);
    }


    public Collection<Professeur> getProfesseurs() {
        // on extrait les profs de la liste des participants
        return this.participantsWithPresence.entrySet().stream()
                .filter(pp -> pp.getKey() instanceof Professeur)
                .map(pp -> ((Professeur) pp.getKey()) )
                .collect(Collectors.toList());
    }

    public void setProfesseurs(Collection<Professeur> professeurs) {
        if (this.participantsWithPresence == null) {
            this.participantsWithPresence = new HashMap<>();
        }

        // on ajoute les profs à la liste des participants
        this.participantsWithPresence.putAll(
                professeurs.stream().collect(Collectors.toMap(p -> p, p->true))
        );
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
