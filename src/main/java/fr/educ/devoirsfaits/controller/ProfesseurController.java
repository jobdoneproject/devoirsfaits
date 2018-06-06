package fr.educ.devoirsfaits.controller;


import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/etablissements/{idEtablissement}/professeurs")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class ProfesseurController {

    @Autowired
    UtilisateurService utilisateurService;

    // Get a Single
    @GetMapping("/{id}")
    public Professeur getById(@PathVariable(value = "id") long idProfesseur) {
        return (Professeur) utilisateurService.find(idProfesseur);
    }

    // Get All By Etablissement
    @GetMapping("")
    public List<Utilisateur> getAllByEtablissement(@PathVariable(value = "idEtablissement") long idEtablissement) {

        List<Utilisateur> professeurs = utilisateurService.findAllByIdEtablissementAndPrivilege( idEtablissement,"Professeur");

        return professeurs;
    }

    // Create a new
    @PostMapping("")
    public String create(@Valid @RequestBody Professeur professeur) {
        professeur.setPassword(utilisateurService.crypt(professeur.getPassword()));

        String message = utilisateurService.save(professeur);

        return message;
    }
    // Update
    @PutMapping("{id}")
    public String update(@PathVariable(value = "id") Long idProfesseur,
                             @Valid @RequestBody Professeur professeurUpdate) {
        Professeur professeur = (Professeur) utilisateurService.update(idProfesseur, professeurUpdate);

        String message = utilisateurService.save(professeur);

        return message;
    }

    //update disponible
    @PutMapping("/{id}/switch")
    public ResponseEntity<?> updateDisponibilite(@PathVariable(value = "id") Long idProfesseur){
        Professeur professeur = (Professeur) utilisateurService.find(idProfesseur);

        professeur.setDisponible(utilisateurService.updateDisponibilite(professeur.isDisponible()));

        utilisateurService.save(professeur);

        return ResponseEntity.ok().build();
    }


    // Delete
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long idProfesseur) {

        utilisateurService.delete(idProfesseur);

        return ResponseEntity.ok().build();
    }
}
