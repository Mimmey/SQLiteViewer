package viewer;

public class TextGenerators {

    public static String generateSelectFrom(String tableName) {
        return ("SELECT * FROM " + tableName + ";");
    }
}
