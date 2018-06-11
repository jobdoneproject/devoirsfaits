package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreneauRepository extends
        JpaRepository<Creneau, Long>,
        CreneauRepositoryCustom {
}
