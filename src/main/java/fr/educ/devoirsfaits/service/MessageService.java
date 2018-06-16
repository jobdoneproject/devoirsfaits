package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Message;
import fr.educ.devoirsfaits.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public String save(Message message){
        String messageErreur;

        try {
            messageRepository.saveAndFlush(message);
            messageErreur = "Entrée validée.";
        } catch (org.springframework.dao.DataIntegrityViolationException e){
            messageErreur = e.getMessage();
        }

        return messageErreur;
    }

    public List<Message> BuildAllMessagesForEleve(long idEleve){

        List<Message> messages = messageRepository.findAllByEleve_IdUtilisateur(idEleve);


        return messages;
    }
}
