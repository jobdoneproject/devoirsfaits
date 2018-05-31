package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {

}
