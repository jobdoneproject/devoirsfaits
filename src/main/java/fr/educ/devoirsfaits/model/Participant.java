package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Participant {

    @Id
    @GeneratedValue
    private long idParticipant;

    private boolean present;

    @OneToOne
    private Eleve eleve;

    @OneToOne
    private Creneau creneau;

    public Participant(boolean present, Eleve eleve, Creneau creneau) {
        this.present = present;
        this.eleve = eleve;
        this.creneau = creneau;
    }

    public Participant() { }

    public long getIdParticipant() {
        return idParticipant;
    }

    /*public void setIdParticipant(long idParticipant) {
        this.idParticipant = idParticipant;
    }*/

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}
