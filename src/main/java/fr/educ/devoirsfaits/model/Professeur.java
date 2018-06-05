package fr.educ.devoirsfaits.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "utilisateur")
@PrimaryKeyJoinColumn(name = "id")
public class Professeur extends Utilisateur {

    public Professeur() {
    }

    public Professeur(long id) {
        super();
        this.setIdUtilisateur(id);
    }


/*
    @ManyToMany(mappedBy="professeurs")
    private Collection<Creneau> creneaux = new ArrayList<>();
*/


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Professeur"));
        return authorities;
    }
}
