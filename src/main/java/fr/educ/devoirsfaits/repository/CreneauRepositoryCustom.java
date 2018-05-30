package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Creneau;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreneauRepositoryCustom {

    @Query(value="SELECT cr FROM Creneau cr " +
            "WHERE cr.dateDebut > :debutSemaine " +
            "AND cr.dateFin < :finSemaine")
    public List<Creneau> findByDateDebutAndDateFin(
            @Param("debutSemaine") long debutSemaine,
            @Param("finSemaine") long finSemaine
    );

}
