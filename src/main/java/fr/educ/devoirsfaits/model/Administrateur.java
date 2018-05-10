package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
@PrimaryKeyJoinColumn(name = "id")
public class Administrateur extends Utilisateur {

    public Administrateur() {
    }
}
