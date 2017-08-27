
public class Bid extends Auction {

    private int bidId;
    private int bidStep;
    private int bidCurrent;
    private int buyerId;
    private int productId;

    public Bid(String bidId, String bidStep, String bidCurrent, String buyerId, String productId) {
        this.bidId = Integer.parseInt(bidId);
        this.bidStep = Integer.parseInt(parseNull(bidStep));
        this.bidCurrent = Integer.parseInt(parseNull(bidCurrent));
        this.buyerId = Integer.parseInt(parseNull(buyerId));
        this.productId = Integer.parseInt(parseNull(productId));
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