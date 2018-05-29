package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fr.educ.devoirsfaits.service.Crypter;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Service
public class UtilisateurService implements UserDetailsService {


    public Boolean updateDisponibiliteUtilisateur(Boolean disponibilite){
        if (disponibilite) return false;
        else return true;
    }

    //à voir si à conserver ? car doublon avec update() plus bas
    @Produces
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, Utilisateur nouvellesDonneesUtilisateur) {

        if (nouvellesDonneesUtilisateur.getNom() != null) {
            utilisateur.setNom(nouvellesDonneesUtilisateur.getNom());
        } else {
            utilisateur.setNom(utilisateur.getNom());
        }

        if (nouvellesDonneesUtilisateur.getPrenom() != null){
            utilisateur.setPrenom(nouvellesDonneesUtilisateur.getPrenom());
        } else {
            utilisateur.setPrenom(utilisateur.getPrenom());
        }

        if (nouvellesDonneesUtilisateur.getMail() != null) {
            utilisateur.setMail(nouvellesDonneesUtilisateur.getMail());
        } else {
            utilisateur.setMail(utilisateur.getMail());
        }

        if (nouvellesDonneesUtilisateur.getTelephone() != null) {
            utilisateur.setTelephone(nouvellesDonneesUtilisateur.getTelephone());
        } else {
            utilisateur.setTelephone(utilisateur.getTelephone());
        }

        if (nouvellesDonneesUtilisateur.getPassword() != null && utilisateur.getPassword() != Crypter.crypt(nouvellesDonneesUtilisateur.getPassword())) {
            utilisateur.setPassword(Crypter.crypt(nouvellesDonneesUtilisateur.getPassword()));
        } else {
            utilisateur.setPassword(utilisateur.getPassword());
        }

        return utilisateur;
    }


    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.saveAndFlush(utilisateur);
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
