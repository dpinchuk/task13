import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BuyerTest {

    private final String buyerId = "1";
    private final String buyerName = "TestName";
    private final String buyerLastname = "TestLastname";
    private final Buyer buyer = new Buyer(this.buyerId, this.buyerName, this.buyerLastname);

    private final int idP = 1;
    private final String nameP = "TestName";
    private final String lastnameP = "TestLastname";

    private final int idN = 2;
    private final String nameN = "FaketName";
    private final String lastnameN = "FakeLastname";

    private String[] idArrayNegative = {null, "`", "q", "ё", " ", "", "test"};

    @Test
    public void testBuyerPositive() {
        Assert.assertEquals(this.idP, this.buyer.getBuyerId());
        Assert.assertEquals(this.nameP, this.buyer.getBuyerName());
        Assert.assertEquals(this.lastnameP, this.buyer.getBuyerLastname());
    }

    @Test
    public void testBuyerNegative() {
        Assert.assertNotEquals(this.idN, this.buyer.getBuyerId());
        Assert.assertNotEquals(this.nameN, this.buyer.getBuyerName());
        Assert.assertNotEquals(this.lastnameN, this.buyer.getBuyerLastname());
    }

    @Test(expected = NumberFormatException.class)
    public void testBuyerNumberFormatExceptionNegative() {
        for (String id : idArrayNegative) {
            new Seller(id, this.buyerName, this.buyerLastname);
        }
    }

}