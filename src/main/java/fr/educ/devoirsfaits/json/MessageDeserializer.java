package fr.educ.devoirsfaits.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Message;
import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.utils.InvalidJsonDataException;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

public class MessageDeserializer extends JsonDeserializer<Message> {

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Message message = new Message();






        JsonNode utilisateurNode = node.get("utilisateur");
        if (node.get("privilege").equals("professeur"))
            parseUtilisateurs(utilisateurNode, Professeur.class);
        message.setUtilisateur(professeurs);

        JsonNode elevesNode = node.get("eleves");
        Map<Eleve, Boolean> eleves = new HashMap<>();
        parseUtilisateurs(elevesNode, Eleve.class, (eleve, jsonNode) -> eleves.put(eleve, jsonNode.get("present").booleanValue()));
        message.setUtilisateur(eleves);
        return null;
    }



    private <T extends Utilisateur> Utilisateur parseUtilisateurs(JsonNode utilisateursNode, Class<T> utilisateurClass) throws InvalidJsonDataException {
        try {
            if (utilisateursNode.size() > 0) {
                Iterator<JsonNode> profsNodesIt = utilisateursNode.iterator();
                T utilisateur = null;
                JsonNode curUtilisateurNode = null;
                while (profsNodesIt.hasNext()) {
                    curUtilisateurNode = profsNodesIt.next();
                    utilisateur = utilisateurClass.newInstance();
                    utilisateur.setIdUtilisateur(curUtilisateurNode.get("idUtilisateur").asLong());
                    addToUtilisateurs.accept(utilisateur, curUtilisateurNode);
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new InvalidJsonDataException("Error while parsing creneau. Could not parse " + utilisateurClass.getSimpleName() + " data : malformed or absent data", e);
        }

    }
}
