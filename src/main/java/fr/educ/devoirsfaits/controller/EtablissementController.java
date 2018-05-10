package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/etablissement")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
public class EtablissementController {

    @Autowired
    EtablissementRepository etablissementRepository;

    // Get All
    @GetMapping("")
    public List<Etablissement> getAllEtablissement() {
        List<Etablissement> etablissementList = etablissementRepository.findAll();
        return etablissementList;
    }

    // Create a new
    @PostMapping("")
    public Etablissement createEtablissement(@Valid @RequestBody Etablissement etablissement) {

        etablissement.setUrlEtablissement();
        return etablissementRepository.save(etablissement);
    }


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
        etablissement.setIdUtilisateur(etablissementDetails.getIdUtilisateur());

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
