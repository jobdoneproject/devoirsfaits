package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.enterprise.inject.Produces;
import java.util.List;

@Service
public class EtablissementService {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Produces
    public Etablissement getByUrl(String etablissementUrl) {

        List<Etablissement> listeEtablissement;
        Etablissement etablissement = null;

        try {
            listeEtablissement = etablissementRepository.findAll();

            for (Etablissement chercheEtablissement : listeEtablissement) {
                if (chercheEtablissement.getUrlEtablissement().equals(etablissementUrl)){
                    etablissement = chercheEtablissement;
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return etablissement;
    }

    public Etablissement getById(long id) {
        return etablissementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etablissement", "id", id));
    }
}
