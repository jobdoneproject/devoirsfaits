package fr.educ.devoirsfaits.controller;

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
    private static String UPLOADED_FOLDER = "/temp/";

    @PostMapping("/etablissements/{id}/eleves/import/")
    public void singleFileUpload(@PathVariable(value= "id") long etablissementId,
            @Valid @RequestBody MultipartFile file) {
        if (file == null || file.isEmpty()) {
            System.err.println("Pas de fichier uploadé ou vide !");
        }

        BufferedReader reader = null;

        try {
            String currentLine = "";
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while (currentLine != null) {
                currentLine = reader.readLine();
                ///////////////temporaire/////////////
                System.out.println(currentLine);
                ////////////////temporaire////////////////
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

    @GetMapping("/etablissements/{id}/eleves/uploadStatus")
    public String uploadStatus(
            @Param("id") long etablissementId
    ) {
        return String.format("/etablissements/%d/eleves/uploadStatus", etablissementId);
    }

}
