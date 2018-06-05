package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etablissement")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class SalleController {

    @Autowired
    SalleService salleService;

    // Get All By Etablissement
    @GetMapping("/{id}/salles")
    public List<Salle> getAllByEtablissement(@PathVariable(value = "id") long idEtablissement) {
        return salleService.findAllByIdEtablissement(idEtablissement);
    }

}
