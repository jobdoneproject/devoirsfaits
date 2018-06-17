package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "utilisateur")
@PrimaryKeyJoinColumn(name = "id")
public class Eleve extends Utilisateur implements Comparable<Eleve>{

    private String classe;

    public Eleve() {
    }

    public Eleve(long id) {
        super();
        this.setIdUtilisateur(id);
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

/*
    @ManyToMany(mappedBy="eleves")
    private Collection<Creneau> creneaux = new ArrayList<>();
*/

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Eleve"));
        return authorities;
    }

    @Override
    public int compareTo(Eleve o) {
        int comparison = 0;
        if(o != null) {
            comparison = this.getNom().compareTo(o.getNom());
        }
        return comparison;
    }
}
