package fr.educ.devoirsfaits.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.educ.devoirsfaits.model.Creneau;
import fr.educ.devoirsfaits.repository.CreneauRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/etablissement")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")

public class CreneauController {

    @Autowired
    CreneauRepository creneauRepository;

    // Get All creneau for selected week
    @GetMapping("/{id}/creneaux")
    public List<Creneau> getWeekCreneaux(
            @PathVariable(value = "id") Long etablissementId,
            @RequestParam(value="year", required=true) int year,
            @RequestParam(value="week", required=true) int week
    ) {
        long[] weekBeginEndTimestamps = getWeekTimestampsFromYearAndId(year, week);

        List<Creneau> creneauList = creneauRepository
                .findByDateDebutAndDateFin(
                        weekBeginEndTimestamps[0],
                        weekBeginEndTimestamps[1]
                );

        return creneauList;
    }



    @GetMapping("/{idEtab}/creneaux/{idCreneau}")
    public Creneau getCreneauxById(
            @PathVariable(value = "idEtab") Long etablissementId,
            @PathVariable(value = "idCreneau") Long creneauId
    ) {

        // TODO idEtablissement INUTILE ??

        return creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau", "id", creneauId));
    }



    @PostMapping("/{idEtab}/creneaux")
    public long createCreneau(@Valid @RequestBody Creneau creneau){

        // TODO Ajout des utilisateurs ???

        ObjectMapper mapper = new ObjectMapper();

        try {
            Creneau creneauJson = mapper.readValue(creneau.toString(), Creneau.class);
            System.out.println(creneauJson);



        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        creneauRepository.save(creneau);

        return creneau.getIdCreneau();
    }



    @DeleteMapping("/{idEtab}/creneaux/{idCreneau}")
    public ResponseEntity<?> deleteCreneaux(
            @PathVariable(value = "idEtab") Long etablissementId,
            @PathVariable(value = "idCreneau") Long creneauId){

        Creneau creneau = creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau", "id", creneauId));
        creneauRepository.delete(creneau);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{idEtab}/creneaux/{idCreneau}")
    public Creneau editCreneaux(
            @Valid @RequestBody Creneau creneauPutted,
            @PathVariable(value = "idEtab") Long etablissementId,
            @PathVariable(value = "idCreneau") Long creneauId){

        Creneau creneau = creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau", "id", creneauId));

        creneau.setDateDebut(creneauPutted.getDateDebut());
        creneau.setDateFin(creneauPutted.getDateFin());
        creneau.setSalle(creneauPutted.getSalle());
        creneauRepository.save(creneau);

        // TODO Faut-il renvoyer JSON Etablissement complet ?

        return creneau;
    }



    private long[] getWeekTimestampsFromYearAndId(int year, int week){
        // Création date selon semaine appelée
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        // Confirm Date créée
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        System.out.println(sdf.format(cal.getTime()));

        // Création timestamps en secondes
        long timestampInMilliseconds = cal.getTimeInMillis();
        String timeStampStringInMilliseconds = String.valueOf(timestampInMilliseconds);
        String timeStampStringInSeconds = timeStampStringInMilliseconds.substring(0, timeStampStringInMilliseconds.length() - 3);
        long timestampInSeconds = Long.parseLong(timeStampStringInSeconds);

        long sevenDaysInSeconds = 7 * 24 * 60 * 60;
        long semaineDebut = timestampInSeconds;
        long semaineFin = semaineDebut + sevenDaysInSeconds;

        long[] weekBeginEndTimestamps = {semaineDebut, semaineFin};
        return weekBeginEndTimestamps;
    }

}
