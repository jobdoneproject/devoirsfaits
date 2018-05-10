package fr.educ.devoirsfaits.model;

import javax.persistence.*;

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

    private long idUtilisateur;

    //@OneToMany(mappedBy="idSalle")
    //private Collection<Salle> salles ;

    //@OneToMany(mappedBy="idUtilisateur")
    //private Collection<Utilisateur> utilisateurs ;

    public Etablissement(String nomEtablissement, String villeEtablissement, long idAdministrateur) {
        this.nomEtablissement = nomEtablissement;
        this.urlEtablissement = this.villeEtablissement.replaceAll(" ","-").toLowerCase() + "-" + this.nomEtablissement.replaceAll(" ","-").toLowerCase();
        this.villeEtablissement = villeEtablissement;
        this.idUtilisateur = idAdministrateur;
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
        this.urlEtablissement =  this.villeEtablissement.replaceAll(" ","-").toLowerCase() + "-" + this.nomEtablissement.replaceAll(" ","-").toLowerCase();
    }

    public String getVilleEtablissement() {
        return villeEtablissement;
    }

    public void setVilleEtablissement(String villeEtablissement) {
        this.villeEtablissement = villeEtablissement;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
