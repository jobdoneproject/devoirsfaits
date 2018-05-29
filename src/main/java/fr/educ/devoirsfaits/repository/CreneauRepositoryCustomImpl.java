package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Creneau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class CreneauRepositoryCustomImpl implements CreneauRepositoryCustom {

    /*@Autowired
    CreneauRepository creneauRepository;*/

    @Override
    /*@Query("SELECT cr.IdCreneau FROM creneau cr " +
            "WHERE cr.DateDebut > 1529274115 " +
            "AND cr.DateFin < 1529751600")*/
    public List<Creneau> findByDateDebutAndDateFin(
            @Param("debutSemaine") long debutSemaine,
            @Param("finSemaine") long finSemaine
    ){
        List<Creneau> creneauxList = null;
        return creneauxList;
    };
    /*public List<Creneau> findByDateDebutAndDateFin(long debutSemaine, long finSemaine) {
        return null;
    }*/
}
