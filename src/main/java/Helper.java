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


public class Helper {

    public static final String UA_FILE = "Зміст.docx";
    public static final String EN_FILE = "Content.docx";
    private static final String DIRECTORY = "C:\\Users\\Serhii\\Desktop\\Зміст.С.С\\";
    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    public static String converter(String file) throws IOException {
        // read and convert to txt, doc or docx file
        String result = null;
        if (new File(file).exists()) {
            FileInputStream fis = new FileInputStream(new File(file));
            Path source = Paths.get(file);

            if (CONTENT_TYPE.equals(Files.probeContentType(source))) { //is a docx
                XWPFDocument doc = new XWPFDocument(fis);
                XWPFWordExtractor extract = new XWPFWordExtractor(doc);
                result = extract.getText();
            } else { //is  a doc
                HWPFDocument doc = new HWPFDocument(fis);
                WordExtractor extractor = new WordExtractor(doc);
                result = extractor.getText();
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
        int returnVal = chooser.showDialog(null, "Find directory");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
        }

        if (!filePath.equals("")) {
            biblio.biblio(filePath + "\\");
            biblio.site(filePath + "\\");
            System.out.println("Path OK");
        } else {
            System.out.println("Error path");
        }
    }
}
