import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindTexForSite {
    ArrayList<String> text = new ArrayList<>();

    public void findTxt(String filePath) throws IOException {
        Helper helper = new Helper();

        Scanner scannerUa = new Scanner(helper.converter(filePath + "Зміст.docx"));
        FindReplace(scannerUa);
//        scannerUa = new Scanner(helper.converter(filePath + "СОДЕРЖАНИЕ.docx"));
//        FindReplace(scannerUa);
        scannerUa = new Scanner(helper.converter(filePath + "Content.docx"));
        FindReplace(scannerUa);
    }

    public void FindReplace(Scanner scannerLg) {
        while (scannerLg.hasNext()) {
            String stTmp = (scannerLg.nextLine());
            Pattern patern = Pattern.compile("\\.,\\s[\\D{3,19}^\\s]\\s[\\D{0,7}^\\s]\\.\\s[\\D{0,7}^\\s]\\.\\s|[\\D{3,19}^\\s]\\s[\\D{0,7}^\\s]\\.\\s[\\D{0,7}^\\s]\\.\\s"); //Поиск по фамилии имени и отчеству | Поиск по фамилии и имени | Поиск по фамилии имени и отчеству повторы
            Matcher mat = patern.matcher(stTmp);
            Pattern paternDouble = Pattern.compile("[\\D{3,19}^\\s]\\s\\D{0,5}\\.\\s"); // По Поиск по фамилии и имени без отчества
            Matcher matD = paternDouble.matcher(stTmp);
            Pattern paternT = Pattern.compile("^\\D{4,15}\\s\\D{4,15}$"); //Поиск названия темы
            Matcher matT = paternT.matcher(stTmp);

            if (mat.find()) {
                String start = stTmp.substring(0, mat.end());
                String end = stTmp.substring(mat.end());
                String fin = "<strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (matD.find()) {
                String start = stTmp.substring(0, matD.end());
                String end = stTmp.substring(matD.end());
                String fin = "<strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (matT.find()) {
                String start = stTmp.substring(0, matT.end());
                String fin = "\n      <p align=\"center\"><strong>" + start + "</strong></p>";
                text.add(fin);

            } else { //если что то не так
                if (stTmp.equals("ЗМІСТ") || stTmp.equals("СОДЕРЖАНИЕ") || stTmp.equals("CONTENT")) {
                    String fin = "\n        <p align=\"center\"><em><strong>" + stTmp + "</strong></em></p>";
                    text.add(fin);
                } else {
                    String fin = "!!!! <strong>" + stTmp + " </strong> <br/>";
                    text.add(fin);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "FindText{" +
                "text=" + text +
                '}';
    }
}
