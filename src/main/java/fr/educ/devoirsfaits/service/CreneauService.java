package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Creneau;
import fr.educ.devoirsfaits.repository.CreneauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreneauService {

    @Autowired
    CreneauRepository creneauRepository;



    // recuperation des creneaux à dupliquer
    public void duplicationSemaines(Long[] TimestampSemainesADupliquer, Long[] TimestampSemainesReceptrices){


        Map<Long,List<Creneau>> semainesCreneauxADupliquer = new HashMap();

        for(long debutDeSemaine : TimestampSemainesADupliquer){

            long finDeSemaine = debutDeSemaine + 3600 * 24 * 7;
            List<Creneau> creneaux = creneauRepository.findByDateDebutAndDateFin(debutDeSemaine, finDeSemaine);
            semainesCreneauxADupliquer.put(debutDeSemaine, creneaux);
        }


        int i = 0;
        for (long timestampSemaineReceptrice : TimestampSemainesReceptrices){

            duplicationCreneaux(timestampSemaineReceptrice, TimestampSemainesADupliquer[i], semainesCreneauxADupliquer.get(TimestampSemainesADupliquer[i]));
            if (i < semainesCreneauxADupliquer.size()) i++;
            else i = 0;
        }
    }


    public void duplicationCreneaux(long debutDeSemaineReceptrice, long debutDeSemaineADupliquer, List<Creneau> creneauADupliquer){

        long intervaleTimestamps = debutDeSemaineReceptrice - debutDeSemaineADupliquer;

        for (Creneau creneau: creneauADupliquer) {
            Creneau creneauDuplique = (Creneau) creneau.clone();
            creneauDuplique.setDateDebut(creneau.getDateDebut() + intervaleTimestamps);
            creneauDuplique.setDateFin(creneau.getDateFin() + intervaleTimestamps);
            creneauDuplique.setIdCreneau(null);

            creneauRepository.save(creneauDuplique);
        }
    }
}
