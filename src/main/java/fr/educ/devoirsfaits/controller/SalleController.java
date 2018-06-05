package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etablissements")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class SalleController {

    @Autowired
    SalleService salleService;

    // Get All By Etablissement
    @GetMapping("/{id}/salles")
    public List<Salle> getAllByEtablissement(@PathVariable(value = "id") long idEtablissement) {
        return salleService.findAllByIdEtablissement(idEtablissement);
    }

    @GetMapping("/{idEtabl}/salles/{idSalle}")
    public Salle getByEtablissementAndByClassroomId(
            @PathVariable(value = "idEtabl") long idEtablissement,
            @PathVariable(value = "idSalle") long idSalle
    ) {
        List<Salle> sallesEtablissement = salleService.findAllByIdEtablissement(idEtablissement);
        return sallesEtablissement.stream().filter(salleCourante -> salleCourante.getIdSalle() == idSalle).limit(1).collect(Collectors.toList()).get(0);
    }

}
