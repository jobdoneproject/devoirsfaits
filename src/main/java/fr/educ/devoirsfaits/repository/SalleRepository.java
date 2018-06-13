package fr.educ.devoirsfaits.repository;


import fr.educ.devoirsfaits.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

}
