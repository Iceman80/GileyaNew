package gileya;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static gileya.Constants.PATTERN_FULL_NAME;
import static gileya.Constants.PATTERN_NAME_AND_SURNAME;
import static gileya.Constants.PATTERN_THEMES;

public class FindTexForSite {
    ArrayList<String> text = new ArrayList<>();

    public void findReplace(List<Scanner> scanners) {
        Scanner scanner = scanners.get(0);
        replasePatern(scanner);
        scanner = scanners.get(1);
        replasePatern(scanner);
    }

    private void replasePatern (Scanner scanner){
        while (scanner.hasNext()) {
            String stTmp = (scanner.nextLine());
            Pattern patern = Pattern.compile(PATTERN_FULL_NAME);
            //Поиск по фамилии имени и отчеству | Поиск по фамилии и имени | Поиск по фамилии имени и отчеству повторы
            Matcher mat = patern.matcher(stTmp);
            Pattern paternDouble = Pattern.compile(PATTERN_NAME_AND_SURNAME);
            // По Поиск по фамилии и имени без отчества
            Matcher matD = paternDouble.matcher(stTmp);
            Pattern paternT = Pattern.compile(PATTERN_THEMES); //Поиск названия темы
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
        return "gileya.FindText{" +
                "text=" + text +
                '}';
    }
}
