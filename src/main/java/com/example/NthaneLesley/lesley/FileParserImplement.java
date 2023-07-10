package com.example.NthaneLesley.lesley;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.net.URI;

@Component
abstract class FileParserImplement implements FileParser{

    private final ProfileRepository profileRepository;
    @Autowired
    public FileParserImplement(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void parseCSV(File csvFile){
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                String surname = fields[1].trim();
                String httpImageLink = fields[2].trim();

                Profile accountProfile = new Profile(name, surname, httpImageLink);
                String base64ImageData = fields[3].trim();
                File imageFile = convertCSVDataToImage(base64ImageData);
                URI imageLink = createImageLink(imageFile);
                String linkImage = String.valueOf(imageLink);
                accountProfile.setHttpImageLink(linkImage);
            }
        } catch(IOException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData){
        byte[] imageBytes = Base64Utils.decodeFromString(base64ImageData);
        File imageFile = new File("/v1/api/image/image.png");
        try(FileOutputStream fos = new FileOutputStream(imageFile)){
            fos.write(imageBytes);
        }catch (IOException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }

        return imageFile;
    }

    @Override
    public URI createImageLink(File fileImage){
        //File imageFile = new File("path/to/save/images/image.png");
        String filename = fileImage.getName();
        String link = "/v1/api/image/" + filename;
        return fileImage.toURI();
    }
}
