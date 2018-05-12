package fr.educ.devoirsfaits.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("account")
public class AccoutController {

    public static final Logger logger = LoggerFactory.getLogger(AdministrateurController.class);

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

    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("user logged "+principal);
        return principal;
    }


}
