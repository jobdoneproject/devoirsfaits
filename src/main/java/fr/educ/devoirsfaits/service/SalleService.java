package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> findAllByIdEtablissement(long idEtablissement) {
        List<Salle> toutesLesSalles = salleRepository.findAll();
        return toutesLesSalles.stream().filter(salleCourante -> salleCourante.getEtablissement().
                getIdEtablissement() == idEtablissement).collect(Collectors.toList());
    }

    public String save(Salle salle) {
        String message;

        try {
            salleRepository.saveAndFlush(salle);
            message = "Entrée validée.";
        } catch (org.springframework.dao.DataIntegrityViolationException e){
            message = e.getMessage();
        }

        return message;
    }
}
