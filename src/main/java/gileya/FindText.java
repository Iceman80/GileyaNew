package gileya;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static gileya.Constants.PATTERN_FULL_NAME;
import static gileya.Constants.PATTERN_NAME_AND_SURNAME;
import static gileya.Constants.PATTERN_THEMES;
import static gileya.Constants.PATTERN_TITLE;

/*
regexp for library
 */
public class FindText {
    ArrayList<String> text = new ArrayList<>();

    public void findReplace(List<Scanner> scanners) {
        Scanner scannerUa = scanners.get(0);
        Scanner scannerEn = scanners.get(1);
        while (scannerUa.hasNext() && scannerEn.hasNext()) {
            String stUa = scannerUa.nextLine();
            String stEn = scannerEn.nextLine();
            Pattern paternT = Pattern.compile(PATTERN_THEMES); //Поиск названия темы
            Matcher matUaTheme = paternT.matcher(stUa);
            Matcher matEnTheme = paternT.matcher(stEn);
            Pattern paternTitle = Pattern.compile(PATTERN_TITLE); //Поиск названия темы
            Matcher matUaTitle = paternTitle.matcher(stUa);
            Matcher matEnTitle = paternTitle.matcher(stEn);

            if (!matUaTheme.find() && !matEnTheme.find() && !matUaTitle.find() && !matEnTitle.find()) {
                Pattern patern1 = Pattern.compile(PATTERN_FULL_NAME); //Поиск инициалов имени и отчества Ua
                Matcher mat1 = patern1.matcher(stUa);
                Pattern patern1En = Pattern.compile(PATTERN_FULL_NAME); //Поиск инициалов имени и отчества En
                Matcher mat1En = patern1En.matcher(stEn);
                Pattern patern2 = Pattern.compile(PATTERN_NAME_AND_SURNAME);//Поиск инициалов имени без отчества Ua
                Matcher mat2 = patern2.matcher(stUa);
                Pattern patern2En = Pattern.compile(PATTERN_NAME_AND_SURNAME);//Поиск инициалов имени без отчества En
                Matcher mat2En = patern2En.matcher(stEn);

                if (mat1.find()) {
                    String start = stUa.substring(0, mat1.end());
                    String endUa = stUa.substring(mat1.end());
                    if (mat1En.find()) {
                        createAndAddSting(start, endUa, stEn, mat1En);
                    } else if (mat2En.find()) {
                        createAndAddSting(start, endUa, stEn, mat2En);
                    }

                } else if (mat2.find()) {
                    String start = stUa.substring(0, mat2.end());
                    String endUa = stUa.substring(mat2.end());
                    if (mat1En.find()) {
                        createAndAddSting(start, endUa, stEn, mat1En);
                    } else if (mat2En.find()) {
                        createAndAddSting(start, endUa, stEn, mat2En);
                    }
                } else { //если что то не так
                    String fin = "!" + stUa + "}{" + stEn + "}";
                    text.add(fin);
                }
            }
        }
    }

    private void createAndAddSting(String start, String endUa, String string, Matcher matcher) {
        String end;
        end = string.substring(matcher.end());
        String fin = start + "[" + endUa + "]{" + end + "}";
        text.add(fin);
    }

    @Override
    public String toString() {
        return "gileya.FindText{" +
                "text=" + text +
                '}';
    }
}
