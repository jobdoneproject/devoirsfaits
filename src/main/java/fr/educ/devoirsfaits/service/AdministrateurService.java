package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Administrateur;
import fr.educ.devoirsfaits.repository.AdministrateurRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurService implements UserDetailsService {


    @Autowired
    AdministrateurRepository administrateurRepository;

    public Administrateur save(Administrateur administrateur) {
        return administrateurRepository.saveAndFlush(administrateur);
    }

    public Administrateur update(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public Administrateur find(String nom) {
        return administrateurRepository.findOneByNom(nom);
    }

    public Administrateur find(Long idAdministrateur) {
        return administrateurRepository.findById(idAdministrateur)
                .orElseThrow(() -> new ResourceNotFoundException("Administrateur", "id", idAdministrateur));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrateur user = find(username);
        return  user;
    }

}
