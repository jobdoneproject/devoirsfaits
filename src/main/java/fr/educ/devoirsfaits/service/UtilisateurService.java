package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.enterprise.inject.Produces;

import static java.util.Collections.emptyList;

@Service
public class UtilisateurService implements UserDetailsService {


    //à voir si à conserver ? car doublon avec update() plus bas
    @Produces
    public Utilisateur updateUtilisateur(Utilisateur nouvelUtilisateur, Utilisateur nouvellesDonneesUtilisateur) {
        nouvelUtilisateur.setNom(nouvellesDonneesUtilisateur.getNom());
        nouvelUtilisateur.setPrenom(nouvellesDonneesUtilisateur.getPrenom());
        nouvelUtilisateur.setMail(nouvellesDonneesUtilisateur.getUsername());
        nouvelUtilisateur.setTelephone(nouvellesDonneesUtilisateur.getTelephone());
        nouvelUtilisateur.setActif(nouvellesDonneesUtilisateur.isActif());
        nouvelUtilisateur.setDisponible(nouvellesDonneesUtilisateur.isDisponible());
        nouvelUtilisateur.setPassword(nouvellesDonneesUtilisateur.getPassword());
        return nouvelUtilisateur;
    }

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.saveAndFlush(utilisateur);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur find(String mail) {
        return utilisateurRepository.findOneByMail(mail);
    }

    public Utilisateur find(Long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", idUtilisateur));
    }


    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
        //Utilisateur user = find(nom);
        Utilisateur user = utilisateurRepository.findOneByMail(nom);
        if (user == null) {
            throw new UsernameNotFoundException(nom);
        }
        return user;
//        return new User(user.getUsername(), user.getPassword(), emptyList());
    }

//    @Override
//    public UserDetails loadUserByUsername(String usernmailame) throws UsernameNotFoundException {
//        Utilisateur utilisateur = utilisateurRepository.findByUsername(mail);
//        if (utilisateur == null) {
//            throw new UsernameNotFoundException(mail);
//        }
//        return new User(utilisateur.getUsername(), utilisateur.getPassword(), emptyList()) {
//        };
//    }
}

