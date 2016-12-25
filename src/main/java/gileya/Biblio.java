package gileya;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static gileya.Constants.BIBIO_HEADER;
import static gileya.Constants.FILE_NAME_FOR_BIBLIO;
import static gileya.Constants.FILE_NAME_FOR_SYTE;
import static gileya.Constants.SITE_HEADER;

public class Biblio {


    public void biblio(String filePath, List<Scanner> scanners) throws IOException {
        FindText findText = new FindText();
        findText.findReplace(scanners);
        saveFile(findText.text, BIBIO_HEADER, FILE_NAME_FOR_BIBLIO, filePath);

    }

    public void site(String filePath, List<Scanner> scanners) throws IOException {
        FindTexForSite findText = new FindTexForSite();
        findText.findReplace(scanners);
        saveFile(findText.text, SITE_HEADER, FILE_NAME_FOR_SYTE, filePath);
    }

    private void saveFile(List<String> texts, String header, String fileName, String filePath) throws IOException {
        for (String tmp : texts) {
            header += tmp + "\n";
        }
        FileUtils.writeStringToFile(new File(filePath + fileName), header);
    }
}
