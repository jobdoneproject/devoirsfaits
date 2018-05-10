package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

    public Administrateur findOneByNom(String nom);

}
