package com.example.NthaneLesley.lesley;

import java.io.File;
import java.net.URI;

public interface FileParser {
    void parseCSV(File csvFile);
    void parserCSV(File csvFile);
    File convertCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);
}