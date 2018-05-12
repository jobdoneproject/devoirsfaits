package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Administrateur;
import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.model.RequetFormulaireAdmin;
import fr.educ.devoirsfaits.repository.AdministrateurRepository;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.service.UtilisateurService;
import fr.educ.devoirsfaits.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("account")
public class AccoutController {

    public static final Logger logger = LoggerFactory.getLogger(AccoutController.class);

    /*
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Administrateur newAdministrateur) {
        if (administrateurService.find(newAdministrateur.getUsername()) != null) {
            logger.error("username Already exist " + newAdministrateur.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newAdministrateur.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }
        //newAdministrateur.setRole("administrateur");

        return new ResponseEntity<Administrateur>(administrateurService.save(newAdministrateur), HttpStatus.CREATED);
    }*/
    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<?> createAdministrateur( @Valid @RequestBody RequetFormulaireAdmin model) {

        Etablissement etablissement = new Etablissement();
        etablissement.setNomEtablissement(model.getEtablissement());
        etablissement.setVilleEtablissement(model.getVille());
        etablissement.setUrlEtablissement();

        etablissementRepository.save(etablissement);


        Administrateur administrateur = new Administrateur();
        administrateur.setNom(model.getNom());
        administrateur.setPrenom(model.getPrenom());
        administrateur.setMail(model.getMail());
        administrateur.setPassword(model.getPassword());
        administrateur.setIdEtablissement(etablissement.getIdEtablissement());
        //administrateurRepository.save(administrateur);
        if (utilisateurService.find(administrateur.getMail()) != null) {
            logger.error("username Already exist " + administrateur.getMail());
            return new ResponseEntity(
                    new CustomErrorType("user with mail " + administrateur.getMail() + "already exist "),
                    HttpStatus.CONFLICT);
        }

        return new ResponseEntity<Administrateur>(administrateurRepository.save(administrateur), HttpStatus.CREATED);
    }
    /*public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.find(newUser.getUsername()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }
        newUser.setRole("USER");

        return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
    }*/


    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("user logged "+principal);
        return principal;
    }


}
