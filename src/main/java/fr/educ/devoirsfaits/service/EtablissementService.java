package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Etablissement;
import fr.educ.devoirsfaits.repository.EtablissementRepository;
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
}
