package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository URepository;

    // Get All
    @GetMapping("/all")
    public List<Utilisateur> getAllUtilisateur() {
        return URepository.findAll();
    }

    // Create a new
    @PostMapping("/ajout")
    public Utilisateur createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return URepository.save(utilisateur);
    }

    // Update

    // Delete
}
