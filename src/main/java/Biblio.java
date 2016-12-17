import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Biblio {


    public void biblio(String filePath) throws IOException {
        FindText findText = new FindText();
        findText.findTxt(filePath);
        String doc =Helper.BIBIO_HEADER;
        for (String tmp : findText.text) {
            doc += tmp + "\n";
        }

        FileUtils.writeStringToFile(new File(filePath + "ZMIST.txt"), doc);
    }

    public void site(String filePath) throws IOException {
        FindTexForSite findTexForSite = new FindTexForSite();
        findTexForSite.findTxt(filePath);
        String doc = Helper.SITE_HEADER;
        for (String tmp : findTexForSite.text) {
            doc += tmp + "\n";
        }

        FileUtils.writeStringToFile(new File(filePath + "Gileya.php"), doc);
    }
}
