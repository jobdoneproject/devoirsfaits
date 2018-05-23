package fr.educ.devoirsfaits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.educ.devoirsfaits.service.Crypter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="role",
        discriminatorType=DiscriminatorType.STRING
)
@Scope("session")
public class Utilisateur implements Serializable, UserDetails {

    /**/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idUtilisateur;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(name="mail", unique = true, nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    private String telephone;

    boolean disponible;

    boolean actif;

    private long idEtablissement ;

    @Inject @Transient
    private Crypter crypt;

    @Column(name="role", insertable = false, updatable = false)
    protected String privilege;
    /**/

    public Utilisateur() { }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = crypt.crypt(password);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public long getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(long idEtablissement) {
            this.idEtablissement = idEtablissement;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override // retourne le mail au lieu du nom pour identification !
    public String getUsername() {
        // TODO Auto-generated method stub
        return mail;
    }

    public String getPrivilege() {
        return privilege;
    }
}
