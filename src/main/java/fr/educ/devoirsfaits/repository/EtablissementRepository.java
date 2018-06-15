package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

//    @Query(SELECT )

}
