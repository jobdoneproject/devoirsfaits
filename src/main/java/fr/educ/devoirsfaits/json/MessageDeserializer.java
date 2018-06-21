package fr.educ.devoirsfaits.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Message;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class MessageDeserializer extends JsonDeserializer<Message> {

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Message message = new Message();

        message.setContenu(node.get("contenu").asText());
        message.setDateMessage(node.get("dateMessage").asLong());

        JsonNode eleveNode = node.get("eleve");
        Eleve eleve = (Eleve) utilisateurService.find(eleveNode.get("idUtilisateur").asLong());
        message.setEleve(eleve);

        JsonNode redacteurNode = node.get("redacteur");
        Utilisateur redacteur = utilisateurService.find(redacteurNode.get("idUtilisateur").asLong());
        message.setRedacteur(redacteur);

        return message;
    }

}
