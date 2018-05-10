package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Administrateur;
import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.repository.AdministrateurRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.service.AdministrateurService;
import fr.educ.devoirsfaits.service.UtilisateurService;
import fr.educ.devoirsfaits.utils.CustomErrorType;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("account")
//@RequestMapping("/administrateur")
//@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
public class AdministrateurController {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    AdministrateurService administrateurService;

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

    public static final Logger logger = LoggerFactory.getLogger(AdministrateurController.class);

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Administrateur newAdministrateur) {
        if (administrateurService.find(newAdministrateur.getUsername()) != null) {
            logger.error("username Already exist " + newAdministrateur.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newAdministrateur.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }
        newAdministrateur.setRole("administrateur");

        return new ResponseEntity<Administrateur>(administrateurService.save(newAdministrateur), HttpStatus.CREATED);
    }

    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("user logged "+principal);
        return principal;
    }
}
