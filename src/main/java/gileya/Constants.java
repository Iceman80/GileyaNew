package gileya;


public final class Constants {
    public static final String UA_FILE = "Зміст.docx";
    public static final String EN_FILE = "Content.docx";
    public static final String FILE_NAME_FOR_SYTE = "Gileya.php";
    public static final String FILE_NAME_FOR_BIBLIO = "ZMIST.txt";
    public static final String CONTENT_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    //    public static final String DIRECTORY = "C:\\Users\\Serhii\\Desktop\\Зміст.С.С\\";
    public static final String DIRECTORY = "C:\\Users\\serhii.yakovlev\\Desktop\\Зміст.С.С\\";

    public static final String SITE_HEADER = "<em><strong>ЗМІСТ - CONTENT </strong></em>\n" + "[cut]<br /></div>";
    public static final String BIBIO_HEADER = "\"Гілея: науковий вісник\" Збірник наукових праць, 2016, Вип. 11 ().\n" +
            "[Титул]\n" +
            "[Зміст]\n";

    
    //Поиск по фамилии имени и отчеству | Поиск по фамилии и имени | Поиск по фамилии имени и отчеству повторы
    public static final String PATTERN_FULL_NAME = "\\.,\\s[\\D{3,19}^\\s]\\s[\\D{0,7}^\\s]\\.\\s[\\D{0,7}^\\s]\\.\\s|" +
            "[\\D{3,19}^\\s]\\s[\\D{0,7}^\\s]\\.\\s[\\D{0,7}^\\s]\\.\\s";
    // По Поиск по фамилии и имени без отчества
    public static final String PATTERN_NAME_AND_SURNAME = "[\\D{3,19}^\\s]\\s\\D{0,5}\\.\\s";
    //Поиск по названию темы
    public static final String PATTERN_THEMES = "^\\D{4,15}\\s\\D{4,15}$";
    public static final String PATTERN_TITLE = "^\\D{4,15}$";

}
