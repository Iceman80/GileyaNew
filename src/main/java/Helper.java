import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Helper {

    public String converter(String file) {
        // read and convert to txt, doc or docx file
        FileInputStream fis;
        String result = null;

        if (file.substring(file.length() - 1).equals("x")) { //is a docx
            try {
                fis = new FileInputStream(new File(file));
                XWPFDocument doc = new XWPFDocument(fis);
                XWPFWordExtractor extract = new XWPFWordExtractor(doc);
                result = extract.getText();
            } catch (IOException e) {

                e.printStackTrace();
            }
        } else { //is not a docx
            try {
                fis = new FileInputStream(new File(file));
                HWPFDocument doc = new HWPFDocument(fis);
                WordExtractor extractor = new WordExtractor(doc);
                result = extractor.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String filePath() throws IOException {
        //find path to file
        String filePath = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showDialog(null, "Find directory");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
        }
        return filePath + "\\";
    }
}
