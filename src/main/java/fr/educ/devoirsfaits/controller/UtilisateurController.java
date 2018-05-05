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
public class UtilisateurController {

    @Autowired
    UtilisateurRepository URepository;

    // Get All
    @GetMapping("/all")
    public List<Utilisateur> getAllUtilisateur() {
        return URepository.findAll();
    }

    // Create a new
    @PostMapping("/add")
    public Utilisateur createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return URepository.save(utilisateur);
    }


    // Get a Single
    @GetMapping("/get/{id}")
    public Utilisateur getUtilisateurById(@PathVariable(value = "id") long utilisateurId) {
        return URepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));
    }

    // Update
    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable(value = "id") Long utilisateurId,
                           @Valid @RequestBody Utilisateur utilisateurDetails) {

        Utilisateur utilisateur = URepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));

        utilisateur.setPassword(utilisateurDetails.getPassword());
        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setPrenom(utilisateurDetails.getPrenom());
        utilisateur.setMail(utilisateurDetails.getMail());
        utilisateur.setDisponible(utilisateurDetails.isDisponible());
        utilisateur.setProfesseur(utilisateurDetails.isProfesseur());
        utilisateur.setAdministrateur(utilisateurDetails.isAdministrateur());
        utilisateur.setTelephone(utilisateurDetails.getTelephone());
        utilisateur.setClasse(utilisateurDetails.getClasse());
        utilisateur.setActif(utilisateurDetails.isActif());
        utilisateur.setIdEtablissement(utilisateurDetails.getIdEtablissement());

        Utilisateur updatedUtilisateur = URepository.save(utilisateur);
        return updatedUtilisateur;
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable(value = "id") Long utilisateurId) {
        Utilisateur utilisateur = URepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));

        URepository.delete(utilisateur);

        return ResponseEntity.ok().build();
    }
}
