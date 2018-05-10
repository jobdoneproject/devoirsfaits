package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Utilisateur;
import org.springframework.stereotype.Service;

import javax.enterprise.inject.Produces;

@Service
public class UtilisateurService {



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
}
