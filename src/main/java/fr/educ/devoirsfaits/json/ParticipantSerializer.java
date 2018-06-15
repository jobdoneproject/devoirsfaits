package fr.educ.devoirsfaits.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.educ.devoirsfaits.model.Utilisateur;

import java.io.IOException;
import java.util.Map;


/**
 * Mapping Json d'une Map associant utilisateur à booléen de présence
 */
public class ParticipantSerializer extends JsonSerializer<Map<Utilisateur, Boolean>> {

    @Override
    public void serialize(Map<Utilisateur, Boolean> participants, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        if (participants != null && !participants.isEmpty()) {
            for (Map.Entry<Utilisateur, Boolean> p: participants.entrySet()) {
                serializeParticipantWithPresence(p.getKey(), p.getValue(), jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndArray();
    }


    private void serializeParticipantWithPresence(Utilisateur participant, Boolean present, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonSerializer<Object> defaultSerializer = serializerProvider.findValueSerializer(participant.getClass());
        jsonGenerator.writeStartObject();

        // this is basically your 'writeAllFields()'-method:
        defaultSerializer.unwrappingSerializer(null).serialize(participant, jsonGenerator, serializerProvider);

        jsonGenerator.writeBooleanField("present", present);
        jsonGenerator.writeEndObject();
    }
}
