package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Creneau;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreneauRepositoryCustom {

    /*public final static String FIND_BETWEEN_TIMESTAMPS =
            "SELECT * " +
            "FROM creneau " +
            "WHERE debutSemaine > dateDebut " +
            "AND finSemaine < dateFin";*/

//    @Query(FIND_BETWEEN_TIMESTAMPS)
    /*@Query("SELECT cr.id_creneau FROM Creneau cr " +
            "WHERE cr.date_debut > :debutSemaine " +
            "AND cr.date_fin < :finSemaine")*/
    /*@Query("SELECT cr FROM Creneau cr " +
        "WHERE cr.DateDebut > 1529274115 " +
        "AND cr.DateFin < 1529751600")*/
    @Query(value="SELECT cr FROM Creneau cr " +
            "WHERE cr.dateDebut > :debutSemaine " +
            "AND cr.dateFin < :finSemaine")
    public List<Creneau> findByDateDebutAndDateFin(
            @Param("debutSemaine") long debutSemaine,
            @Param("finSemaine") long finSemaine
    );

    /*List<Creneau> findByDateDebutAndDateFin(
            long debutSemaine,
            long finSemaine
    );*/

}
