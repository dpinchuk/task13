import java.util.ArrayList;
import java.util.List;

public abstract class Auction {

    final String EXIT = "0";

    List<Seller> sellersList = new ArrayList<>();
    List<Product> productsList = new ArrayList<>();
    List<Buyer> buyersList = new ArrayList<>();
    List<Bid> bidsList = new ArrayList<>();

    String[] getData(String listNote) {
        return listNote.split("\\|");
    }

    String parseNull(String data) {
        if (data.toLowerCase().equals("null")) {
            return "0";
        } else {
            return data;
        }
    }

}