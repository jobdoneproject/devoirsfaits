package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {

}
