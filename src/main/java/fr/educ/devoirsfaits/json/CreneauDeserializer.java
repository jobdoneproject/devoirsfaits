package fr.educ.devoirsfaits.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.educ.devoirsfaits.model.*;
import fr.educ.devoirsfaits.utils.InvalidJsonDataException;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Deserialize un creneau au format Json en un bean Creneau
 */
public class CreneauDeserializer extends JsonDeserializer<Creneau> {

    @Override
    public Creneau deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Creneau creneau = new Creneau();
        if (node.hasNonNull("idCreneau")) {
            creneau.setIdCreneau(node.get("idCreneau").asLong());
        }
        creneau.setDateDebut(node.get("dateDebut").asLong());
        creneau.setDateFin(node.get("dateFin").asLong());

        Salle salle = new Salle();
        JsonNode salleNode = node.get("salle");
        salle.setIdSalle(salleNode.get("idSalle").asLong());
        salle.setNom(salleNode.get("nom").asText());
        creneau.setSalle(salle);

        JsonNode professeursNode = node.get("professeurs");
        List<Professeur> professeurs = new ArrayList<>();
        parseUtilisateurs(professeursNode, Professeur.class, (prof, jsonNode) -> professeurs.add(prof));
        creneau.setProfesseurs(professeurs);

        JsonNode elevesNode = node.get("eleves");
        Map<Eleve, Boolean> eleves = new HashMap<>();
        parseUtilisateurs(elevesNode, Eleve.class, (eleve, jsonNode) -> eleves.put(eleve, jsonNode.get("present").booleanValue()));
        creneau.setElevesParticipant(eleves);


        return creneau;
    }


    /**
     * Parse les utilisateurs et les ajoute à une collection donnée
     *
     * @param utilisateursNode  Le noeud Json contenant le tableau d'utilisateurs
     * @param utilisateurClass  Le type d'utilisateurs à parsé (Eleve|Professeur)
     * @param addToUtilisateurs La lambda chargée d'ajouter les utilisateurs parsés à la collection
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private <T extends Utilisateur> void parseUtilisateurs(JsonNode utilisateursNode, Class<T> utilisateurClass, BiConsumer<T, JsonNode> addToUtilisateurs) throws InvalidJsonDataException {
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
