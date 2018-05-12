package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Administrateur;
import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.repository.AdministrateurRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.service.UtilisateurService;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/administrateur")
public class AdministrateurController {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    UtilisateurService utilisateurService;

    // Get All
    @GetMapping("")
    public List<Administrateur> getAllAdministrateur() {
        List<Administrateur> listeAdministration = administrateurRepository.findAll();

        return listeAdministration;
    }

    // Get by etablissement
    @GetMapping("etablissement/{id}")
    public Administrateur getAdministrateurByetablissement(@PathVariable(value = "id") long etablissementId) {
        List<Administrateur> listeAdministration = administrateurRepository.findAll();
        Administrateur findAdministration = null;
        for (Administrateur administrateur : listeAdministration){
            if (administrateur.getIdEtablissement() == etablissementId){
                findAdministration = administrateur;
            }
        }
        return findAdministration;
    }

    // Get a Single
    @GetMapping("/{id}")
    public Administrateur getAdministrateurById(@PathVariable(value = "id") long administrateurId) {

        return administrateurRepository.findById(administrateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrateur", "id", administrateurId));
    }


    /*
    Pour creer un nouvel administrateur, il faut en premier creer l'etablissement qui renvoie son idEtablissement
     */
    // Create a new
    @PostMapping("")
    public String createAdministrateur(@Valid @RequestBody Administrateur administrateur) {

        String message;
        try {
            administrateurRepository.save(administrateur);
            message = "entrée validée";
        } catch(DataIntegrityViolationException e){
            message = e.getMessage();
        }
        return message;
    }

    // Update
    @PutMapping("{id}")
    public Administrateur updateAdministrateur(@PathVariable(value = "id") Long administrateurId,
                                       @Valid @RequestBody Professeur nouvellesDonneesAdministrateur) {

        Administrateur administrateur = administrateurRepository.findById(administrateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrateur", "id", administrateurId));

        administrateur = (Administrateur) utilisateurService.updateUtilisateur( administrateur, nouvellesDonneesAdministrateur);

        Administrateur updatedAdministrateur = administrateurRepository.save(administrateur);
        return updatedAdministrateur;
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdministrateur(@PathVariable(value = "id") Long administrateurId) {
        Administrateur administrateur = administrateurRepository.findById(administrateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrateur", "id", administrateurId));

        administrateurRepository.delete(administrateur);

        return ResponseEntity.ok().build();
    }



 }
