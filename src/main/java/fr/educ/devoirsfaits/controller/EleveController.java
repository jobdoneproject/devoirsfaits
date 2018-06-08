package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/etablissements/{idEtablissement}/eleves")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class EleveController {


    @Autowired
    UtilisateurService utilisateurService;

    // Get All By Etablissement
    @GetMapping("")
    public List<Utilisateur> getAllByEtablissement(@PathVariable(value = "idEtablissement") long idEtablissement) {

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
    @PutMapping("")
    public void updateAll( @Valid @RequestBody Eleve[] eleves) {

        for (Eleve eleve: eleves) {

            eleve = (Eleve) utilisateurService.update(eleve.getIdUtilisateur(), eleve);
            eleve.setClasse(eleve.getClasse());

            utilisateurService.save(eleve);
        }
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
    @PutMapping("/{id}/switch")
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

    @Transactional
    @DeleteMapping("")
    public void deleteUsers(@Valid @RequestBody Eleve[] eleves) {
        for (Eleve eleve: eleves) {
            long idEleve = eleve.getIdUtilisateur();
            utilisateurService.delete(idEleve);
        }
    }

}
