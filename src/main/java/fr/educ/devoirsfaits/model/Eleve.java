package fr.educ.devoirsfaits.model;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
@PrimaryKeyJoinColumn(name = "id")
public class Eleve extends Utilisateur {

    private String classe;

    public Eleve() {
        super();
    }
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
