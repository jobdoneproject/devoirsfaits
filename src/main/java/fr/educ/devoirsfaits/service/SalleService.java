package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Salle;
import fr.educ.devoirsfaits.repository.SalleRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public Salle find(Long idSalle) {
        return salleRepository.findById(idSalle)
                .orElseThrow(() -> new ResourceNotFoundException("Salle", "id", idSalle));
    }
}
