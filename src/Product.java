
public class Product {

    private int productId;
    private String productName;
    private int productStartPrice;
    private int productSalePrice;
    private int idSeller;

    public Product(String productId, String productName, String productStartPrice, String productSalePrice, String idSeller) {
        this.productId = Integer.parseInt(productId);
        this.productName = productName;
        this.productStartPrice = Integer.parseInt(Tools.parseNull(productStartPrice));
        this.productSalePrice = Integer.parseInt(Tools.parseNull(productSalePrice));
        this.idSeller = Integer.parseInt(Tools.parseNull(idSeller));
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductStartPrice() {
        return productStartPrice;
    }

    public int getProductSalePrice() {
        return productSalePrice;
    }

    public int getIdSeller() {
        return idSeller;
    }

}