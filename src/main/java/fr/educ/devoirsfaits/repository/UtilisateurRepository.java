package fr.educ.devoirsfaits.repository;

import fr.educ.devoirsfaits.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long> {
    public Utilisateur findOneByMail(String mail);
    public List<Utilisateur> findAllByIdEtablissementAndPrivilege(long idEtablissement, String privilege);
    public void deleteByIdUtilisateur(long idUtilisateur);
}

