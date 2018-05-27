package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Creneau;
import fr.educ.devoirsfaits.repository.CreneauRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.repository.AdministrateurRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/etablissement")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")

public class EtablissementController {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    CreneauRepository creneauRepository;



    // Etablissement ----------------------------------------------------------------

    // Get All
    @GetMapping("")
    public List<Etablissement> getAllEtablissement() {
        List<Etablissement> etablissementList = etablissementRepository.findAll();
        return etablissementList;
    }

    /*
    Lors de la creation de l'établissement, l'id de ce dernier et renvoyée pour permettre de créer son administrateur
     */

    // Create a new
    @PostMapping("")
    public long createEtablissement(@Valid @RequestBody Etablissement etablissement) {

        etablissement.setUrlEtablissement();
        etablissementRepository.save(etablissement);

        return etablissement.getIdEtablissement();
    }


    // Create a new etablissement plus administrateur
    /*@PostMapping("")
    public String createEtablissement(@Valid @RequestBody Object[] object) {
        String message = "";
        Etablissement etablissement = (Etablissement) object[0];
        Administrateur administrateur = (Administrateur) object[1];
        try {
            etablissement.setUrlEtablissement();
            etablissementRepository.save(etablissement);
            message += "Etablissement inscrit";

            try {
                long idEtablissement = etablissement.getIdEtablissement();
                administrateur.setIdEtablissement(idEtablissement);
                administrateurRepository.save(administrateur);
                message += " Administrateur inscrit";
            } catch(DataIntegrityViolationException e){
                message = e.getMessage();
            }
        } catch (DataIntegrityViolationException e) {
            message = e.getMessage();
        }

        return message;
    }*/





    // Get a Single
    @GetMapping("/{id}")
    public Etablissement getEtablissementById(@PathVariable(value = "id") long etablissementId) {
        return etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));
    }

    // Update
    @PutMapping("/{id}")
    public Etablissement updateEtablissement(@PathVariable(value = "id") Long etablissementId,
                                 @Valid @RequestBody Etablissement etablissementDetails) {

        Etablissement etablissement = etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));


        etablissement.setNomEtablissement(etablissementDetails.getNomEtablissement());
        etablissement.setVilleEtablissement(etablissementDetails.getVilleEtablissement());
        etablissement.setUrlEtablissement();

        Etablissement updatedEtablissement = etablissementRepository.save(etablissement);
        return updatedEtablissement;
    }
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEtablissement(@PathVariable(value = "id") Long etablissementId) {
        Etablissement etablissement = etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));

        etablissementRepository.delete(etablissement);

        return ResponseEntity.ok().build();
    }

    // ! Etablissement ----------------------------------------------------------------





    // Créneau ----------------------------------------------------------------

    // Get All creneau for selected week
    @RequestMapping("/{id}/creneaux")
    public List<Creneau> getWeekCreneaux(
            @PathVariable(value = "id") Long etablissementId,
            @RequestParam(value="year", required=true) int year,
            @RequestParam(value="week", required=true) int week
    ) {
        // Conversion semaine en timestamp en secondes

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, 25);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        long timestampInMilliseconds = cal.getTimeInMillis();
        String timeStampStringInMilliseconds = String.valueOf(timestampInMilliseconds);
        String timeStampStringInSeconds = timeStampStringInMilliseconds.substring(0, timeStampStringInMilliseconds.length() - 3);
        long timestampInSeconds = Long.parseLong(timeStampStringInSeconds);

        long sevenDays = 7 * 24 * 60 * 60;
        long semaineDebut = timestampInSeconds;
        long semaineFin = semaineDebut + sevenDays;




        // https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-creating-database-queries-with-the-query-annotation/

//        https://stackoverflow.com/questions/41152938/java-spring-return-json-object-array#41153043



        List<Creneau> creneauList = creneauRepository.findAll();
        return creneauList;
    }

    // ! Créneau ----------------------------------------------------------------
}
