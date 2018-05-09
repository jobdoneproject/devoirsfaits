package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.ResourceNotFoundException;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    // Get All
    @GetMapping("")
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    // Create a new
    @PostMapping("")
    public Utilisateur createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }


    // Get a Single
    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable(value = "id") long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));
    }

    // Update
    @PutMapping("/{id}")
    public boolean updateUtilisateur(@PathVariable(value = "id") Long utilisateurId,
                           @Valid @RequestBody Utilisateur utilisateurDetails) {

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));

        if (utilisateurDetails.getPassword() != null){
            utilisateur.setPassword(utilisateurDetails.getPassword());
        }
        if (utilisateurDetails.getNom() != null){
            utilisateur.setNom(utilisateurDetails.getNom());
        }
        if (utilisateurDetails.getPrenom() != null) {
            utilisateur.setPrenom(utilisateurDetails.getPrenom());
        }
        if (utilisateurDetails.getMail() != null) {
            utilisateur.setMail(utilisateurDetails.getMail());
        }
        if (utilisateurDetails.getTelephone() != null) {
            utilisateur.setTelephone(utilisateurDetails.getTelephone());
        }

        utilisateur.setActif(utilisateurDetails.isActif());

        utilisateur.setIdEtablissement(utilisateurDetails.getIdEtablissement());

        utilisateurRepository.save(utilisateur);
        return true;
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable(value = "id") Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));

        utilisateurRepository.delete(utilisateur);

        return ResponseEntity.ok().build();
    }

}
