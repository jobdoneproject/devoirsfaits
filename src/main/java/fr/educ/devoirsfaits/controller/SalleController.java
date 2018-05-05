package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/salle")
public class SalleController {

    @Autowired
    SalleRepository URepository;

    // Get All
    @GetMapping("/all")
    public List<Salle> getAllSalle() {
        return URepository.findAll();
    }

    // Create a new
    @PostMapping("/add")
    public Salle createSAlle(@Valid @RequestBody Salle salle) {
        return URepository.save(salle);
    }

    // Get a Single
    @GetMapping("/get/{id}")
    public Salle getSAlleById(@PathVariable(value = "id") long salleId) {
        return URepository.findById(salleId)
                .orElseThrow(() -> new ResourceNotFoundException("Salle", "id", salleId));
    }

    // Update
    @PutMapping("/update/{id}")
    public Salle updateSalle(@PathVariable(value = "id") Long salleId,
                                         @Valid @RequestBody Salle salleDetails) {

        Salle salle = URepository.findById(salleId)
                .orElseThrow(() -> new ResourceNotFoundException("Salle", "id", salleId));

        salle.setNom(salleDetails.getNom());
        salle.setIdEtablissement(salleDetails.getIdEtablissement());

        Salle updatedSalle = URepository.save(salle);
        return updatedSalle;
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSalle(@PathVariable(value = "id") Long salleId) {
        Salle salle = URepository.findById(salleId)
                .orElseThrow(() -> new ResourceNotFoundException("Salle", "id", salleId));

        URepository.delete(salle);

        return ResponseEntity.ok().build();
    }
}
