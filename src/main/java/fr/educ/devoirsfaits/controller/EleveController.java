package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/eleve")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
public class EleveController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    // Get All
    @GetMapping("")
    public List<Utilisateur> getAllEleve() {
        List<Utilisateur> listeUtilisateur = utilisateurRepository.findAll();



        for (Iterator<Utilisateur> it = listeUtilisateur.iterator(); it.hasNext();){
            Utilisateur utilisateur = it.next();

            if (utilisateur.isAdministrateur()){
                it.remove();
            }else if (utilisateur.isProfesseur()){
                it.remove();
            }
        }
        return listeUtilisateur;
    }

    // Create a new
    @PostMapping("")
    public String createEleve(@Valid @RequestBody Utilisateur eleve) {
    String message;
        try {
            utilisateurRepository.save(eleve);
             message = "entrée validée";
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            message = e.getMessage();
        }
        return message;
    }
}
