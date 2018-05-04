package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Pojo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PojoRepository extends JpaRepository<Pojo, Long> {

}
