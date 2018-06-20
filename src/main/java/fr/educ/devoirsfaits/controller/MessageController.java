package fr.educ.devoirsfaits.controller;


import fr.educ.devoirsfaits.model.Message;
import fr.educ.devoirsfaits.repository.MessageRepository;
import fr.educ.devoirsfaits.service.MessageService;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/etablissements/{idEtablissement}/messages")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageService messageService;

    // Get a Single
    @GetMapping("/{id}")
    public Message getById(@PathVariable(value = "id") long idMessage) {
        return messageRepository.findById(idMessage)
                .orElseThrow(() -> new ResourceNotFoundException("Message", "id", idMessage));
    }

    // get tous les messages d'un eleve
    @GetMapping("/eleves/{id}")
    public List<Message> getAllByEleve(@PathVariable(value = "id") long idEleve) {
        return  messageService.BuildAllMessagesForEleve(idEleve);
    }

    @PostMapping("")
    public String createMessage(@Valid @RequestBody Message message){

        System.out.println("stop");
        return messageService.save(message);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long idMessage) {

        messageRepository.deleteByIdMessage(idMessage);

        return ResponseEntity.ok().build();
    }


}
