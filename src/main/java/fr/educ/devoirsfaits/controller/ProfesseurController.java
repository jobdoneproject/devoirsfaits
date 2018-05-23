package fr.educ.devoirsfaits.controller;


import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.repository.ProfesseurRepository;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/professeur")
public class ProfesseurController {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    ProfesseurRepository professeurRepository;

    @Qualifier
    public UtilisateurService utilisateurService;

    // Get All
    @GetMapping("")
    public List<Professeur> getAllProfesseur() {
        List<Professeur> listeProfesseur = professeurRepository.findAll();

        return listeProfesseur;
    }

    // Get a Single
    @GetMapping("/{id}")
    public Professeur getProfesseurById(@PathVariable(value = "id") long professeurId) {
        Professeur professeur = professeurRepository.findById(professeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Professeur", "id", professeurId));
        return professeur;
    }

    // Get All By Etablissement
    @GetMapping("/etablissement/{id}")
    public List<Professeur> getAllProfesseurByEtablissement(@PathVariable(value = "id") long etablissementId) {
        Etablissement etablissement = etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", etablissementId));
        List<Professeur> listeProfesseur = professeurRepository.findAll();
        for (Iterator<Professeur> it = listeProfesseur.iterator(); it.hasNext();){
            Professeur professeur = it.next();
            if (professeur.getIdEtablissement() != etablissement.getIdEtablissement()){
                it.remove();
            }
        }
        return listeProfesseur;
    }

    // Create a new
    @PostMapping("")
    public String createprofesseur(@Valid @RequestBody Professeur professeur) {
        String message;
        try {
            professeurRepository.save(professeur);
            message = "entrée validée";
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            message = e.getMessage();
        }
        return message;
    }
    // Update
    @PutMapping("{id}")
    public Professeur updateProfesseur(@PathVariable(value = "id") Long professeurId,
                             @Valid @RequestBody Professeur nouvellesDonneesProfesseur) {

        Professeur professeur = professeurRepository.findById(professeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Professeur", "id", professeurId));
        professeur = (Professeur) utilisateurService.updateUtilisateur( professeur, nouvellesDonneesProfesseur);
        Professeur updatedProfesseur = professeurRepository.save(professeur);
        return updatedProfesseur;
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesseur(@PathVariable(value = "id") Long professeurId) {
        Professeur professeur = professeurRepository.findById(professeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Professeur", "id", professeurId));
        professeurRepository.delete(professeur);
        return ResponseEntity.ok().build();
    }
}
