import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblio {


    public void biblio(String filePath) throws IOException {
        FindText findText = new FindText();
        findText.findReplace(returnScanner(filePath));
        saveFile(findText.text, Helper.BIBIO_HEADER,Helper.FILE_NAME_FOR_BIBLIO,filePath);

    }

    public void site(String filePath) throws IOException {
        FindTexForSite findText = new FindTexForSite();
        findText.findReplace(returnScanner(filePath));
        saveFile(findText.text,Helper.SITE_HEADER,Helper.FILE_NAME_FOR_SYTE,filePath);
    }

    private void saveFile(List<String> texts, String header, String fileName, String filePath) throws IOException {
        for (String tmp : texts) {
            header += tmp + "\n";
        }
        FileUtils.writeStringToFile(new File(filePath + fileName), header);
    }

    private List<Scanner> returnScanner (String filePath) throws IOException {
        List<Scanner> scanners = new ArrayList<>();
        Scanner scannerUa = new Scanner(Helper.converter(filePath + Helper.UA_FILE));
        Scanner scannerEn = new Scanner(Helper.converter(filePath + Helper.EN_FILE));
        scanners.add(scannerUa);
        scanners.add(scannerEn);
        return scanners;
    }
}
