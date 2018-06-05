package fr.educ.devoirsfaits.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.service.EtablissementService;
import fr.educ.devoirsfaits.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etablissements")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class SalleController {

    @Autowired
    SalleService salleService;

    @Autowired
    EtablissementService etablissementService;

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

    @PostMapping("/{idEtabl}/salles")
    public String create(@PathVariable(value = "idEtabl") long idEtablissement, @Valid @RequestBody @JsonDeserialize Salle salle){
        Etablissement etablissement = etablissementService.getById(idEtablissement);
        salle.setEtablissement(etablissement);
        return salleService.save(salle);
    }

    @PutMapping("/{idEtabl}/salles/{idSalle}")
    public String update(
            @PathVariable(value = "idEtabl") long idEtablissement,
            @PathVariable(value = "idSalle") long idSalle,
            @Valid @RequestBody Salle salleUpdate
    ) {
        String message;
        Salle salle = salleService.update(idSalle, salleUpdate);
        if (salle.getEtablissement().getIdEtablissement() != idEtablissement) {
            message = "Cette salle n'appartient pas à votre établissement.";
        } else {
            message = salleService.save(salle);
        }
        return message;
    }

    @Transactional
    @DeleteMapping("/{idEtabl}/salles/{idSalle}")
    public String delete(
            @PathVariable(value = "idEtabl") long idEtablissementRequete,
            @PathVariable(value = "idSalle") long idSalle
    ) {
        String message;

        Salle salleASupprimer = salleService.findById(idSalle);
        long idEtablissementSalleDAO = salleASupprimer.getEtablissement().getIdEtablissement();
        if (idEtablissementSalleDAO != idEtablissementRequete){
            message = "Cette salle n'appartient pas à votre établissement.";
        }
        else {
            salleService.delete(idSalle);
            message = "Salle supprimée.";
        }

        return message;
    }

}
