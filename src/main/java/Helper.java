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

    public String converter(String file) throws IOException {
        // read and convert to txt, doc or docx file
        String result = null;
        if (new File(file).exists()) {
            FileInputStream fis = new FileInputStream(new File(file));
            Path source = Paths.get(file);
            if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                    .equals(Files.probeContentType(source))) { //is a docx
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

    public String filePath() throws IOException {
        //find path to file
        String filePath = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showDialog(null, "Find directory");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
        }
        return filePath + "\\";
    }
}
