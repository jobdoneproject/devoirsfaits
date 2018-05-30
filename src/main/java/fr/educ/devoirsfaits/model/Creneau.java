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
/*@FilterDef(name="utilisateurPrivilegeFilter",
            parameters=@ParamDef(
                name="utilisateurPrivilegeFilterParam",
                type="string"))
@Filters({@Filter(
        name="utilisateurPrivilegeFilter",
        condition="privilege = Eleve",
        deduceAliasInjectionPoints = false
)})*/
@FilterDef(name="utilisateurPrivilegeFilter",
        parameters=@ParamDef(
            name="privilegeParam",
            type="string"), defaultCondition = "Eleve")
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
    /*@JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Filter(
            name="utilisateurPrivilegeFilter",
            condition="privilege = Eleve"
    )
            @FilterJoinTable(
                    name="utilisateur",
                    condition = ":privilege = Eleve")*/
    @JoinTable(
            name = "participant",
            joinColumns = {@JoinColumn(name = "id_creneau")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilisateur")})
    @Filter(
            name="utilisateurPrivilegeFilter",
            condition="role = :privilegeParam"
    )
    @JsonIgnore
    Collection<Utilisateur> utilisateurs = new ArrayList<>();



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
        List<Eleve> eleves = new ArrayList<>();
        for (Utilisateur utilisateur: this.utilisateurs) {
            if (utilisateur instanceof Eleve) {
                eleves.add((Eleve) utilisateur);
            }
        }
        return eleves;
    }

    public List<Professeur> getProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        for (Utilisateur utilisateur: this.utilisateurs) {
            if (utilisateur instanceof Professeur) {
                professeurs.add((Professeur) utilisateur);
            }
        }
        return professeurs;
    }

}
