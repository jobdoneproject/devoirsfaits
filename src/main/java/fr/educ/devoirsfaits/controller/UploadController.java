package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Professeur;
import fr.educ.devoirsfaits.model.Utilisateur;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiFunction;

@Controller
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UploadController {

    private final static int INDEX_COLONNE_NOM = 0;
    private final static int INDEX_COLONNE_PRENOM = 1;
    private final static int INDEX_COLONNE_EMAIL = 2;
    private final static int INDEX_COLONNE_CLASSE = 3;

    @Autowired
    UtilisateurService utilisateurService;



    @PostMapping("/etablissements/{id}/eleves/import/")
    public void uploadFileForEleves(@PathVariable(value= "id") long etablissementId,
            @Valid @RequestBody MultipartFile file) {
        uploadFileIntoUtlisateurs(etablissementId, file, (csvDataLine, idEtablissement) -> mapCsvToEleve(csvDataLine.split(","), idEtablissement));
    }

    @PostMapping("/etablissements/{id}/professeurs/import/")
    public void uploadFileForProfesseurs(@PathVariable(value= "id") long etablissementId,
                                          @Valid @RequestBody MultipartFile file) {
        uploadFileIntoUtlisateurs(etablissementId, file, (csvDataLine, idEtablissement) -> mapCsvToProfesseur(csvDataLine.split(","), idEtablissement));
    }

    private <T extends Utilisateur> T mapCsvToUtilisateur(String[] csvData, long etablissementId, Class<T> typeUtilisateur){
        String nom = csvData[INDEX_COLONNE_NOM].toUpperCase();
        String prenomMinuscule = csvData[INDEX_COLONNE_PRENOM].toLowerCase();
        String email = csvData[INDEX_COLONNE_EMAIL];
        String motDePasse = "123";

        T utilisateur = null;
        try {
            utilisateur = typeUtilisateur.newInstance();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(capitalize(prenomMinuscule));
            utilisateur.setMail(email);
            utilisateur.setPassword(motDePasse);
            utilisateur.setIdEtablissement(etablissementId);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return utilisateur;
    }

    private Eleve mapCsvToEleve(String[] csvData, long etablissementId) {
        Eleve eleve = mapCsvToUtilisateur(csvData, etablissementId, Eleve.class);
        String classe = supprimerEspaces(csvData[INDEX_COLONNE_CLASSE]);
        eleve.setClasse(classe);

        return eleve;
    }

    private Professeur mapCsvToProfesseur(String[] csvData, long etablissementId) {
        Professeur professeur = mapCsvToUtilisateur(csvData, etablissementId, Professeur.class);
        return professeur;
    }

    private void uploadFileIntoUtlisateurs(long etablissementId, MultipartFile file,
                                           BiFunction<String, Long, Utilisateur> mapCsvToUtilisateur){
        if (file == null || file.isEmpty()) {
            System.err.println("Pas de fichier uploadé ou vide !");
        }

        BufferedReader reader = null;

        try {
            String currentLine = "";
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            reader.readLine(); // On évite la ligne d'en-tête
            while (currentLine != null) {
                currentLine = reader.readLine();

                Utilisateur utilisateurCourant = mapCsvToUtilisateur.apply(currentLine, etablissementId);
                utilisateurService.save(utilisateurCourant);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String supprimerEspaces(String entree) {
        StringBuilder stringToReturn = new StringBuilder();
        for (char currentChar : entree.toCharArray()){
            if (!Character.isSpaceChar(currentChar)) {
                stringToReturn.append(currentChar);
            }
        }
        return stringToReturn.toString();
    }

    private String capitalize(String input){
        return String.format(
                "%c%s",
                input.toUpperCase().charAt(0),
                input.toLowerCase().substring(1)
        );
    }

}
