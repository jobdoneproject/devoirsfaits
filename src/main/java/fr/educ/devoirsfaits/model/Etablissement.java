package fr.educ.devoirsfaits.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"villeEtablissement", "nomEtablissement"})})
public class Etablissement {

    @Id
    @GeneratedValue
    private long idEtablissement;

    @Column(nullable = false)
    private String nomEtablissement;

    @Column(unique = true, nullable = false)
    private String urlEtablissement;

    @Column(nullable = false)
    private String villeEtablissement;

    @OneToOne(cascade=CascadeType.ALL)
    private Utilisateur administrateur ;

    @OneToMany(mappedBy="idSalle")
    private Collection<Salle> salles ;

    @OneToMany(mappedBy="idUtilisateur")
    private Collection<Utilisateur> utilisateurs ;

    public Etablissement(String nomEtablissement, String villeEtablissement, Utilisateur administrateur) {
        this.nomEtablissement = nomEtablissement;
        this.urlEtablissement = "https://devoirsfaits.fr/" + this.villeEtablissement.replaceAll(" ","-") + "-" + this.nomEtablissement.replaceAll(" ","-");
        this.villeEtablissement = villeEtablissement;
        this.administrateur = administrateur;
    }

    public Etablissement() { }

    public long getIdEtablissement() {
        return idEtablissement;
    }

    /*public void setIdEtablissement(long idEtablissement) {
        this.idEtablissement = idEtablissement;
    }*/

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public String getUrlEtablissement() {
        return urlEtablissement;
    }

    public void setUrlEtablissement() {
        this.urlEtablissement = "https://devoirsfaits.fr/" + this.villeEtablissement.replaceAll(" ","-") + "-" + this.nomEtablissement.replaceAll(" ","-");
    }

    public String getVilleEtablissement() {
        return villeEtablissement;
    }

    public void setVilleEtablissement(String villeEtablissement) {
        this.villeEtablissement = villeEtablissement;
    }

    public Utilisateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Utilisateur administrateur) {
        this.administrateur = administrateur;
    }
}
