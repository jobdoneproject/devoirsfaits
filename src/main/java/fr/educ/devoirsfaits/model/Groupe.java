package fr.educ.devoirsfaits.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Groupe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idGroupe;

    @Column(nullable = false)
    private String nom;

    //@ManyToMany(mappedBy="groupes")
    //private Collection<Utilisateur> utilisateurs ;

    public Groupe(String nom, Collection<Utilisateur> utilisateurs) {
        this.nom = nom;
 //       this.utilisateurs = utilisateurs;
    }

    public Groupe() {
    }

    public long getIdGroupe() {
        return idGroupe;
    }

    /*public void setIdGroupe(long idGroupe) {
        this.idGroupe = idGroupe;
    }*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   /* public Collection<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }*/
}
