package fr.educ.devoirsfaits.controller;

import fr.educ.devoirsfaits.model.Eleve;
import fr.educ.devoirsfaits.model.Professeur;
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
    public void singleFileUploadForEleves(@PathVariable(value= "id") long etablissementId,
            @Valid @RequestBody MultipartFile file) {
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

                Eleve eleveCourant = convertirEnEleve(currentLine, etablissementId);
                utilisateurService.save(eleveCourant);
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

    @PostMapping("/etablissements/{id}/professeurs/import/")
    public void singleFileUploadForProfesseurs(@PathVariable(value= "id") long etablissementId,
                                          @Valid @RequestBody MultipartFile file) {
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

                Professeur professeurCourant = convertirEnProfesseur(currentLine, etablissementId);
                utilisateurService.save(professeurCourant);
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


    private Eleve convertirEnEleve(String currentLine, long etablissementId) {
        String champsCSV [] = currentLine.split(",");
        String nom = champsCSV[INDEX_COLONNE_NOM].toUpperCase();
        String prenomMinuscule = champsCSV[INDEX_COLONNE_PRENOM].toLowerCase();
        String classe = supprimerEspaces(champsCSV[INDEX_COLONNE_CLASSE]);
        String email = champsCSV[INDEX_COLONNE_EMAIL];
        String motDePasse = "123";

        Eleve eleveToReturn = new Eleve();
        eleveToReturn.setNom(nom);
        eleveToReturn.setPrenom(capitalize(prenomMinuscule));
        eleveToReturn.setClasse(classe);
        eleveToReturn.setMail(email);
        eleveToReturn.setPassword(motDePasse);
        eleveToReturn.setIdEtablissement(etablissementId);

        return eleveToReturn;
    }

    private Professeur convertirEnProfesseur(String currentLine, long etablissementId) {
        String champsCSV [] = currentLine.split(",");
        String nom = champsCSV[INDEX_COLONNE_NOM].toUpperCase();
        String prenomMinuscule = champsCSV[INDEX_COLONNE_PRENOM].toLowerCase();
        String email = champsCSV[INDEX_COLONNE_EMAIL];
        String motDePasse = "123";

        Professeur professeurToReturn = new Professeur();
        professeurToReturn.setNom(nom);
        professeurToReturn.setPrenom(capitalize(prenomMinuscule));
        professeurToReturn.setMail(email);
        professeurToReturn.setPassword(motDePasse);
        professeurToReturn.setIdEtablissement(etablissementId);

        return professeurToReturn;
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

    @GetMapping("/etablissements/{id}/eleves/uploadStatus")
    public String uploadStatus(
            @Param("id") long etablissementId
    ) {
        return String.format("/etablissements/%d/eleves/uploadStatus", etablissementId);
    }

}
