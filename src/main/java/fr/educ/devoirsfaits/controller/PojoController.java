package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Pojo;
import fr.educ.devoirsfaits.repository.PojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PojoController {

    @Autowired
    PojoRepository pojoRepository;

    // Get All Pojo
    @GetMapping("/pojos")
    public List<Pojo> getAllPojo() {
        return pojoRepository.findAll();
    }
    // Create a new Pojo
    @PostMapping("/pojos")
    public Pojo createNote(@Valid @RequestBody Pojo pojo) {
        return pojoRepository.save(pojo);
    }
    // Create a new Pojo
    @GetMapping("/pojo/{first_name}")
    @ResponseBody
    public Pojo createNote(@PathVariable(value = "first_name") String first_name) {
        Pojo pojo = new Pojo();
        pojo.setLast_name(first_name);
        System.out.println(pojo.toString());

        pojoRepository.save(pojo);

        return pojo;
    }



    /* Get a Single Pojo
    @GetMapping("/notes/{id}")
    public Pojo getNoteById(@PathVariable(value = "id") Long pojoId) {
        return pojoRepository.findById(pojoId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", pojoId));
    }*/

    // Update a Pojo

    // Delete a Poj
}
