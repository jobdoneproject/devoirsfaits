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
public class EtablissementController {

    @Autowired
    EtablissementRepository etablissementRepository;

    // Get All
    @GetMapping("/all")
    public List<Etablissement> getAllEtablissement() {
        return etablissementRepository.findAll();
    }

    // Create a new
    @PostMapping("/add")
    public Etablissement createEtablissement(@Valid @RequestBody Etablissement etablissement) {
        etablissement.setUrlEtablissement();
        return etablissementRepository.save(etablissement);
    }


    // Get a Single
    @GetMapping("/get/{id}")
    public Etablissement getEtablissementById(@PathVariable(value = "id") long etablissementId) {
        return etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));
    }

    // Update
    @PutMapping("/update/{id}")
    public Etablissement updateEtablissement(@PathVariable(value = "id") Long etablissementId,
                                 @Valid @RequestBody Etablissement etablissementDetails) {

        Etablissement etablissement = etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));


        etablissement.setNomEtablissement(etablissementDetails.getNomEtablissement());
        etablissement.setVilleEtablissement(etablissementDetails.getVilleEtablissement());
        etablissement.setUrlEtablissement();
        etablissement.setAdministrateur(etablissementDetails.getAdministrateur());

        Etablissement updatedEtablissement = etablissementRepository.save(etablissement);
        return updatedEtablissement;
    }
    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEtablissement(@PathVariable(value = "id") Long etablissementId) {
        Etablissement etablissement = etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));

        etablissementRepository.delete(etablissement);

        return ResponseEntity.ok().build();
    }
}
