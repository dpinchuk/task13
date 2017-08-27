
public class Seller {

    private int sellerId;
    private String sellerName;
    private String sellerLastname;

    public Seller(String sellerId, String sellerName, String sellerLastname) {
        this.sellerId = Integer.parseInt(sellerId);
        this.sellerName = sellerName;
        this.sellerLastname = sellerLastname;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerLastname() {
        return sellerLastname;
    }

}