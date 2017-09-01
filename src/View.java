import java.util.List;

public class View {

    public final String[] addSellerString = {
            "id",
            "[Seller <Name>]",
            "[Seller <Lastname>]"
    };
    public final String[] addProductString = {
            "id",
            "[Product <Name>]",
            "[Product <Start Price>]",
            "[Product <Sale Price>]",
            "[Seller_ID] "
    };
    public final String[] addBuyerString = {
            "id",
            "[Buyer <Name>]",
            "[Buyer <Lastname>]"
    };
    public final String[] addBidString = {
            "id",
            "[Bid <Step>]",
            "[Bid <Current>]",
            "[Buyer_ID] ",
            "[Product_ID] "
    };

    public final String viewActions =
            "Select an Item:" + "\n" +
                    "[0]  -> Exit from Auction" + "\n" +
                    "[1]  -> View <Sellers>" + "\n" +
                    "[2]  -> View <Products>" + "\n" +
                    "[3]  -> View <Buyers>" + "\n" +
                    "[4]  -> View <Bids>" + "\n" +
                    "[5]  -> Add new <Seller>" + "\n" +
                    "[6]  -> Add new <Product>" + "\n" +
                    "[7]  -> Add new <Buyer>" + "\n" +
                    "[8]  -> Add new <Bid>" + "\n" +
                    "[9]  -> Edit <Seller>" + "\n" +
                    "[10] -> Edit <Product>" + "\n" +
                    "[11] -> Edit <Buyer>" + "\n" +
                    "[12] -> Edit <Bid>" + "\n" +
                    "[13] -> Delete <Seller>" + "\n" +
                    "[14] -> Delete <Product>" + "\n" +
                    "[15] -> Delete <Buyer>" + "\n" +
                    "[16] -> Delete <Bid>" + "\n";

    public void printTableData(List<String> listQuery, String tableName, String[] columnNames, String format) {
        System.out.println("\n" + "[" + tableName + "]");
        String[] fFormat = format.split(" ");
        for (int i = 0; i < columnNames.length; i++) {
            System.out.printf(fFormat[i], columnNames[i]);
        }
        System.out.println();
        String[] dataFormat;
        for (int i = 0; i < listQuery.size(); i++) {
            dataFormat = Tools.getData(listQuery.get(i));
            for (int j = 0; j < columnNames.length; j++) {
                System.out.printf(fFormat[j], dataFormat[j]);
            }
            System.out.println();
        }
    }

}