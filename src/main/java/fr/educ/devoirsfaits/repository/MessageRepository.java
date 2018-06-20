package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

     List<Message> findAllByEleve_IdUtilisateur(long idEleve);
     void deleteByIdMessage(long idMessage);
}