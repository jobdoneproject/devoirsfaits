package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Creneau;
import fr.educ.devoirsfaits.repository.CreneauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
            if (i < semainesCreneauxADupliquer.size() - 1) i++;
            else i = 0;
        }
    }


    public void duplicationCreneaux(long debutDeSemaineReceptrice, long debutDeSemaineADupliquer, List<Creneau> creneauADupliquer){

        long intervaleTimestamps = debutDeSemaineReceptrice - debutDeSemaineADupliquer;

        for (Creneau creneau: creneauADupliquer) {
            Creneau creneauDuplique = new Creneau();//(Creneau) creneau.clone();
            creneauDuplique.setElevesParticipant(creneau.getElevesParticipant());
            creneauDuplique.setProfesseurs(creneau.getProfesseurs());
            creneauDuplique.setDateDebut(creneau.getDateDebut() + intervaleTimestamps);
            creneauDuplique.setDateFin(creneau.getDateFin() + intervaleTimestamps);
            creneauDuplique.setSalle(creneau.getSalle());
            creneauDuplique.setIdCreneau(null);

            creneauRepository.save(creneauDuplique);
        }
    }
}
