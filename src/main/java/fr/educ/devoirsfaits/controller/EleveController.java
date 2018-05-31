package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.EleveRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/eleve")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class EleveController {


    @Autowired
    UtilisateurService utilisateurService;

    // Get All By Etablissement
    @GetMapping("/etablissement/{id}")
    public List<Utilisateur> getAllByEtablissement(@PathVariable(value = "id") long idEtablissement) {

        List<Utilisateur> eleves = utilisateurService.findAllByIdEtablissementAndPrivilege( idEtablissement,"Eleve");

        return eleves;
    }

    // Get a Single
    @GetMapping("/{id}")
    public Eleve getById(@PathVariable(value = "id") long idEleve) {

        return (Eleve) utilisateurService.find(idEleve);
    }

    // Create a new
    @PostMapping("")
    public String create(@Valid @RequestBody Eleve eleve) {

        eleve.setPassword(utilisateurService.crypt(eleve.getPassword()));

        String message = utilisateurService.save(eleve);

        return message;
    }

    // Update
    @PutMapping("/{id}")
    public String update(@PathVariable(value = "id") Long idEleve,
                             @Valid @RequestBody Eleve eleveUpdate) {

        Eleve eleve = (Eleve) utilisateurService.update(idEleve, eleveUpdate);
        eleve.setClasse(eleveUpdate.getClasse());

        String message = utilisateurService.save(eleve);

        return message;
    }

    //update disponible
    @PutMapping("/disponible/{id}")
    public ResponseEntity<?> updateDisponibilite(@PathVariable(value = "id") Long idEleve){
        Eleve eleve = (Eleve) utilisateurService.find(idEleve);


        eleve.setDisponible(utilisateurService.updateDisponibilite(eleve.isDisponible()));

        utilisateurService.save(eleve);

        return ResponseEntity.ok().build();
    }

    // Delete
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long idEleve) {

        utilisateurService.delete(idEleve);


    }
}
