package fr.educ.devoirsfaits.service;

import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.repository.UtilisateurRepository;
import fr.educ.devoirsfaits.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Boolean updateDisponibilite(Boolean disponibilite){
        if (disponibilite) return false;
        else return true;
    }

    public Utilisateur update(long id, Utilisateur utilisateurUpdate) {

        Utilisateur utilisateur = find(id);

        utilisateur.setNom(utilisateurUpdate.getNom());
        utilisateur.setPrenom(utilisateurUpdate.getPrenom());
        utilisateur.setMail(utilisateurUpdate.getMail());
        utilisateur.setTelephone(utilisateurUpdate.getTelephone());
        utilisateur.setDisponible(utilisateurUpdate.isDisponible());

        if (utilisateur.getPassword().equals(utilisateurUpdate.getPassword())) {
            utilisateur.setPassword(utilisateurUpdate.getPassword());
        } else {
            utilisateur.setPassword(Crypter.crypt(utilisateurUpdate.getPassword()));
        }

        return utilisateur;
    }

    public String save(Utilisateur utilisateur) {
        String message;

        try {
            utilisateurRepository.saveAndFlush(utilisateur);
            message = "entrée validée";
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            message = e.getMessage();
        }
        return message;
    }

    public List<Utilisateur> findAllByIdEtablissementAndPrivilege(long idEtablissement, String privilege){
        List<Utilisateur> utilisateurs= utilisateurRepository.findAllByIdEtablissementAndPrivilege(idEtablissement, privilege);
        return utilisateurs;
    }

    public Utilisateur find(String mail) {
        return utilisateurRepository.findOneByMail(mail);
    }

    public Utilisateur find(Long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", idUtilisateur));
    }


    public void delete(long idUtilisateur){
        utilisateurRepository.deleteByIdUtilisateur(idUtilisateur);

    }

    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
        Utilisateur user = find(nom);
        return  user;
    }



    private  MessageDigest digester;

    public String crypt(String str) {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        byte[] hash = digester.digest(
                str.getBytes(StandardCharsets.UTF_8));
        String MD5Cryter = new String(Hex.encode(hash));

        return MD5Cryter;
    }
}
