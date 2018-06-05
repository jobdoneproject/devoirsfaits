package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

//    @Query(SELECT )

}
