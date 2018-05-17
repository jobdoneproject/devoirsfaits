package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.repository.AdministrateurRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/etablissement")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")

public class EtablissementController {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

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
}
