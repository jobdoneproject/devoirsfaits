package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.repository.EleveRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/eleve")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
public class EleveController {


    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    EleveRepository eleveRepository;

    public UtilisateurService utilisateurService = new UtilisateurService();

    // Get All
    @GetMapping("")
    public List<Eleve> getAllEleve() {
        List<Eleve> listeEleve = eleveRepository.findAll();

        return listeEleve;
    }
    // Get All By Etablissement
    @GetMapping("/etablissement/{id}")
    public List<Eleve> getAllEleveByEtablissement(@PathVariable(value = "id") long etablissementId) {

        List<Eleve> listeEleve = eleveRepository.findAll();

        for (Iterator<Eleve> it = listeEleve.iterator(); it.hasNext();){
            Eleve eleve = it.next();
            if (eleve.getIdEtablissement() != etablissementId){
                it.remove();
            }
        }
        return listeEleve;
    }

    // Get a Single
    @GetMapping("/{id}")
    public Eleve getEleveById(@PathVariable(value = "id") long eleveId) {

        return eleveRepository.findById(eleveId)
                    .orElseThrow(() -> new ResourceNotFoundException("Eleve", "id", eleveId));
        }

    // Create a new
    @PostMapping("")
    public String createEleve(@Valid @RequestBody Eleve eleve) {
    String message;
        try {
            eleveRepository.save(eleve);
             message = "entrée validée";
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            message = e.getMessage();
        }
        return message;
    }

    // Update
    @PutMapping("{id}")
    public Eleve updateEleve(@PathVariable(value = "id") Long eleveId,
                             @Valid @RequestBody Eleve nouvellesDonneesEleve) {

        Eleve eleve = eleveRepository.findById(eleveId)
                .orElseThrow(() -> new ResourceNotFoundException("eleve", "id", eleveId));

        eleve = (Eleve) utilisateurService.updateUtilisateur( eleve, nouvellesDonneesEleve);

        eleve.setClasse(nouvellesDonneesEleve.getClasse());

        Eleve updatedEleve = eleveRepository.save(eleve);
        return updatedEleve;
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEleve(@PathVariable(value = "id") Long eleveId) {
        Eleve eleve = eleveRepository.findById(eleveId)
                .orElseThrow(() -> new ResourceNotFoundException("Eleve", "id", eleveId));

        eleveRepository.delete(eleve);

        return ResponseEntity.ok().build();
    }
}
