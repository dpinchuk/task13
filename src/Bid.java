
public class Bid {

    private int bidId;
    private int bidStep;
    private int bidCurrent;
    private int buyerId;
    private int productId;

    public Bid(String bidId, String bidStep, String bidCurrent, String buyerId, String productId) {
        this.bidId = Integer.parseInt(bidId);
        this.bidStep = Integer.parseInt(Tools.parseNull(bidStep));
        this.bidCurrent = Integer.parseInt(Tools.parseNull(bidCurrent));
        this.buyerId = Integer.parseInt(Tools.parseNull(buyerId));
        this.productId = Integer.parseInt(Tools.parseNull(productId));
    }

    public int getBidId() {
        return bidId;
    }

    public int getBidStep() {
        return bidStep;
    }

    public int getBidCurrent() {
        return bidCurrent;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getProductId() {
        return productId;
    }

}