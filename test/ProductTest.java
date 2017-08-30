import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ProductTest {

    private final String productId = "1";
    private final String productName = "TestProduct";
    private final String productStartPrice = "1000";
    private final String productSalePrice = "0";
    private final String idSeller = "1";
    private final Product product = new Product(this.productId, this.productName, this.productStartPrice, this.productSalePrice, this.idSeller);

    private final int idP = 1;
    private final String nameP = "TestProduct";
    private final int pPriceP = 1000;
    private final int sPriceP = 0;
    private final int sIdP = 1;

    private final int idN = 2;
    private final String nameN = "FakeProduct";
    private final int pPriceN = 1001;
    private final int sPriceN = 1;
    private final int sIdN = 2;

    private String[] idArrayNegative = {null, "`", "q", "ё", " ", "", "test"};

    @Test
    public void testProductPositive() {
        Assert.assertEquals(this.idP, this.product.getProductId());
        Assert.assertEquals(this.nameP, this.product.getProductName());
        Assert.assertEquals(this.pPriceP, this.product.getProductStartPrice());
        Assert.assertEquals(this.sPriceP, this.product.getProductSalePrice());
        Assert.assertEquals(this.sIdP, this.product.getIdSeller());
    }

    @Test
    public void testProductNegative() {
        Assert.assertNotEquals(this.idN, this.product.getProductId());
        Assert.assertNotEquals(this.nameN, this.product.getProductName());
        Assert.assertNotEquals(this.pPriceN, this.product.getProductStartPrice());
        Assert.assertNotEquals(this.sPriceN, this.product.getProductSalePrice());
        Assert.assertNotEquals(this.sIdN, this.product.getIdSeller());
    }

    @Test(expected = NumberFormatException.class)
    public void testBuyerNumberFormatExceptionNegative() {
        for (String number : idArrayNegative) {
            new Product(number, this.productName, this.productStartPrice, this.productSalePrice, this.idSeller);
            new Product(this.productId, this.productName, number, this.productSalePrice, this.idSeller);
            new Product(this.productId, this.productName, this.productStartPrice, number, this.idSeller);
            new Product(this.productId, this.productName, this.productStartPrice, this.productSalePrice, number);
        }
    }

}