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
    private Utilisateur eleve;

    @OneToOne
    private Creneau creneau;

    public Participant(boolean present, Utilisateur eleve, Creneau creneau) {
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

    public Utilisateur getEleve() {
        return eleve;
    }

    public void setEleve(Utilisateur eleve) {
        this.eleve = eleve;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}
