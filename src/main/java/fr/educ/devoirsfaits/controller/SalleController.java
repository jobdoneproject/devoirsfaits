package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salle")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class SalleController {
    @Autowired
    private SalleService salleService;

    @GetMapping("/{id}")
    public Salle getById(@PathVariable(value = "id") long idSalle) {
        return salleService.find(idSalle);
    }
}
