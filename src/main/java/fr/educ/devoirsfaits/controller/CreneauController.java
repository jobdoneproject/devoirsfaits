package fr.educ.devoirsfaits.controller;


import fr.educ.devoirsfaits.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Creneau;
import fr.educ.devoirsfaits.repository.CreneauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/creneau")
public class CreneauController {

    @Autowired
    CreneauRepository creneauRepository;

    // Get All
    @GetMapping("/all")
    public List<Creneau> getAllCreneau() {
        return creneauRepository.findAll();
    }

    // Create a new
    @PostMapping("/add")
    public Creneau createCreneau(@Valid @RequestBody Creneau creneau) {
        return creneauRepository.save(creneau);
    }

    // Get a Single
    @GetMapping("/get/{id}")
    public Creneau getCreneauById(@PathVariable(value = "id") long creneauId) {
        return creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau", "id", creneauId));
    }

    // Update
    @PutMapping("/update/{id}")
    public Creneau updateCreneau(@PathVariable(value = "id") Long creneauId,
                             @Valid @RequestBody Creneau creneauDetails) {

        Creneau creneau = creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau", "id", creneauId));


        creneau.setDateCreneau(creneauDetails.getDateCreneau());
        creneau.setHeureDebut(creneauDetails.getHeureDebut());
        creneau.setHeureFin(creneauDetails.getHeureFin());

        Creneau updatedCreneau = creneauRepository.save(creneau);
        return updatedCreneau;
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCreneau(@PathVariable(value = "id") Long creneauId) {
        Creneau creneau = creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau", "id", creneauId));

        creneauRepository.delete(creneau);

        return ResponseEntity.ok().build();
    }
}
