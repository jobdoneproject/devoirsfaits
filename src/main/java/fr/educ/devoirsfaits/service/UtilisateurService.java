package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.enterprise.inject.Produces;

@Service
public class UtilisateurService implements UserDetailsService {


    //à voir si à conserver ? car doublon avec update() plus bas
    @Produces
    public Utilisateur updateUtilisateur(Utilisateur nouvelUtilisateur, Utilisateur nouvellesDonneesUtilisateur) {


        nouvelUtilisateur.setNom(nouvellesDonneesUtilisateur.getNom());
        nouvelUtilisateur.setPrenom(nouvellesDonneesUtilisateur.getPrenom());
        nouvelUtilisateur.setMail(nouvellesDonneesUtilisateur.getMail());
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
        Utilisateur user = find(nom);
        return  user;
    }
}
