package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long> {
    public Utilisateur findOneByNom(String nom);

}

