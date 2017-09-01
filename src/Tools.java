
public class Tools {

    public static final String EXIT = "0";

    public static String[] getData(String listNote) {
        return listNote.split("\\|");
    }

    public static String parseNull(String data) {
        if (data.toLowerCase().equals("null")) {
            return "0";
        } else {
            return data;
        }
    }

}