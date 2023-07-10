package com.example.NthaneLesley.lesley;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageController {
    private final FileParser fileParser;


    public ImageController(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    @PostMapping("/parse-csv")
    public void parseCSV(@RequestParam("file") MultipartFile file){
        try {
            File csvFile = convertMultipartFileToFile(file);
            fileParser.parserCSV(csvFile);
        }catch (IOException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }
    }

    @GetMapping("/{name}/{surname}/{filename:.+}")
    public FileSystemResource getHttpImageLink(@PathVariable String name,
                                               @PathVariable String surname,
                                               @PathVariable String filename){

        String imagePath = "/v1/api/image/" + name + "_" + surname + "/" + filename;
        return  new FileSystemResource(imagePath);
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException{
        File convertedFiles = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFiles)){
            fos.write(file.getBytes());
        }catch(IOException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }
        return convertedFiles;
    }
}
