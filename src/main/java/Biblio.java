import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Biblio {
    private Helper helper = new Helper();
    private String filePath =  helper.filePath();

    public Biblio() throws IOException {
    }

    public void biblio() throws IOException {
        FindText findText = new FindText();
        findText.findTxt(filePath);
        String doc="\"Гілея: науковий вісник\" Збірник наукових праць, 2016, Вип. 10 ().\n" +
                "[Титул]\n" +
                "[Зміст]\n";
        for (String tmp : findText.text) {
            doc+=tmp+"\n";
        }
        FileUtils.writeStringToFile(new File(filePath+"ZMIST.txt"), doc);
    }

    public void site() throws IOException {
        FindTexForSite findTexForSite=new FindTexForSite();
        findTexForSite.findTxt(filePath);
        String doc="<em><strong>ЗМІСТ - CONTENT </strong></em>\n" + "[cut]<br /></div>";
        for (String tmp : findTexForSite.text) {
            doc+=tmp+"\n";
        }
        FileUtils.writeStringToFile(new File(filePath+"Gileya.php"), doc);
    }
}
