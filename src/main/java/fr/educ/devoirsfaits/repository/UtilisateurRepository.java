package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Utilisateur;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
