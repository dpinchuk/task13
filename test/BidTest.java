import org.junit.Assert;
import org.junit.Test;

public class BidTest {

    private String bidId = "1";
    private String bidStep = "1000";
    private String bidCurrent = "10000";
    private String buyerId = "1";
    private String productId = "1";
    private final Bid bid = new Bid(this.bidId, this.bidStep, this.bidCurrent, this.buyerId, this.productId);

    private final int idP = 1;
    private final int stepP = 1000;
    private final int currentP = 10000;
    private final int bIdP = 1;
    private final int pIdP = 1;

    private final int idN = 2;
    private final int stepN = 2000;
    private final int currentN = 30000;
    private final int bIdN = 2;
    private final int pIdN = 2;

    private String[] idArrayNegative = {null, "`", "q", "ё", " ", "", "test"};

    @Test
    public void testProductPositive() {
        Assert.assertEquals(this.idP, this.bid.getBidId());
        Assert.assertEquals(this.stepP, this.bid.getBidStep());
        Assert.assertEquals(this.currentP, this.bid.getBidCurrent());
        Assert.assertEquals(this.bIdP, this.bid.getBuyerId());
        Assert.assertEquals(this.pIdP, this.bid.getProductId());
    }

    @Test
    public void testProductNegative() {
        Assert.assertNotEquals(this.idN, this.bid.getBidId());
        Assert.assertNotEquals(this.stepN, this.bid.getBidStep());
        Assert.assertNotEquals(this.currentN, this.bid.getBidCurrent());
        Assert.assertNotEquals(this.bIdN, this.bid.getBuyerId());
        Assert.assertNotEquals(this.pIdN, this.bid.getProductId());
    }

    @Test(expected = NumberFormatException.class)
    public void testBuyerNumberFormatExceptionNegative() {
        for (String number : idArrayNegative) {
            new Bid(number, this.bidStep, this.bidCurrent, this.buyerId, this.productId);
            new Bid(this.bidId, number, this.bidCurrent, this.buyerId, this.productId);
            new Bid(this.bidId, this.bidStep, number, this.buyerId, this.productId);
            new Bid(this.bidId, this.bidStep, this.bidCurrent, number, this.productId);
            new Bid(this.bidId, this.bidStep, this.bidCurrent, this.buyerId, number);
        }
    }

}