import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SellerTest {

    private final String sellerId = "1";
    private final String sellerName = "TestName";
    private final String sellerLastname = "TestLastname";
    private final Seller seller = new Seller(this.sellerId, this.sellerName, this.sellerLastname);

    private final int idP = 1;
    private final String nameP = "TestName";
    private final String lastnameP = "TestLastname";

    private final int idN = 2;
    private final String nameN = "FaketName";
    private final String lastnameN = "FakeLastname";

    private String[] idArrayNegative = {null, "`", "q", "ё", " ", "", "test"};

    @Test
    public void testSellerPositive() {
        Assert.assertEquals(this.idP, this.seller.getSellerId());
        Assert.assertEquals(this.nameP, this.seller.getSellerName());
        Assert.assertEquals(this.lastnameP, this.seller.getSellerLastname());
    }

    @Test
    public void testSellerNegative() {
        Assert.assertNotEquals(this.idN, this.seller.getSellerId());
        Assert.assertNotEquals(this.nameN, this.seller.getSellerName());
        Assert.assertNotEquals(this.lastnameN, this.seller.getSellerLastname());
    }

    @Test(expected = NumberFormatException.class)
    public void testSellerNumberFormatExceptionNegative() {
        for (String id : idArrayNegative) {
            new Seller(id, this.sellerName, this.sellerLastname);
        }
    }

}