package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    public Utilisateur findOneByMail(String mail);

}
