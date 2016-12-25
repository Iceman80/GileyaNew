package gileya;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static gileya.Constants.CONTENT_TYPE_DOCX;
import static gileya.Constants.DIRECTORY;
import static gileya.Constants.EN_FILE;
import static gileya.Constants.UA_FILE;


public class Helper {

    private static String converter(String file) throws IOException {
        // read and convert to txt, doc or docx file
        String result = null;
        if (new File(file).exists()) {
            try (FileInputStream fis = new FileInputStream(new File(file))) {
                Path source = Paths.get(file);

                if (CONTENT_TYPE_DOCX.equals(Files.probeContentType(source))) {
                    XWPFDocument doc = new XWPFDocument(fis);
                    XWPFWordExtractor extract = new XWPFWordExtractor(doc);
                    result = extract.getText();
                } else { //is  a doc
                    HWPFDocument doc = new HWPFDocument(fis);
                    WordExtractor extractor = new WordExtractor(doc);
                    result = extractor.getText();
                }
            }
        }
        return result;
    }

    public static void filePath() throws IOException {
        //find path to file
        Biblio biblio = new Biblio();
        String filePath = "";
        JFileChooser chooser = new JFileChooser(DIRECTORY);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showDialog(null, "Select directory");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath()+ "\\";
        }

        if (!filePath.isEmpty()) {
            biblio.biblio(filePath, returnScanner(filePath));
            biblio.site(filePath, returnScanner(filePath));
            System.out.println("Path OK");
        } else {
            System.out.println("Error path");
        }
    }

    private static List<Scanner> returnScanner(String filePath) throws IOException {
        List<Scanner> scanners = new ArrayList<>();
        Scanner scannerUa = new Scanner(converter(filePath + UA_FILE));
        Scanner scannerEn = new Scanner(converter(filePath + EN_FILE));
        scanners.add(scannerUa);
        scanners.add(scannerEn);
        return scanners;
    }
}
