import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ViewTest extends OutputTest {

    private View view;
    private final String[] stringTestAddSellerString  = {
            "id",
            "[Seller <Name>]",
            "[Seller <Lastname>]"
    };

    private final String[] stringTesAddProductString = {
            "id",
            "[Product <Name>]",
            "[Product <Start Price>]",
            "[Product <Sale Price>]",
            "[Seller_ID] "
    };

    private final String[] stringTestAddBuyerString = {
            "id",
            "[Buyer <Name>]",
            "[Buyer <Lastname>]"
    };

    private final String[] stringTestAddBidString = {
            "id",
            "[Bid <Step>]",
            "[Bid <Current>]",
            "[Buyer_ID] ",
            "[Product_ID] "
    };

    private final String[] stringTesAddBidString = {
            "id",
            "[Bid <Step>]",
            "[Bid <Current>]",
            "[Buyer_ID] ",
            "[Product_ID] "
    };

    private final String stringViewActions =
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

    private final String stringNegative = "text";
    private final List<String> listQueryP = Arrays.asList("1|Dima|Pinchuk", "2|Evgeniy|Tyapunoff", "2|Sukin|Kot");
    private final String tableName = "TestTable";
    private final String[] columnNames = {"TestColumn1", "TestColumn2", "TestColumn3"};
    private final String format = "%15s %15s %15s";

    private final List<String> listQueryN = Arrays.asList("1DimaPinchuk", "2EvgeniyTyapunoff", "2SukinKot");

    @Test
    public void testAddSellerString0() throws IOException {
        view = new View();
        System.out.print(view.addSellerString[0]);
        Assert.assertEquals(stringTestAddSellerString[0], output.toString());
    }

    @Test
    public void testAddSellerString1() throws IOException {
        view = new View();
        System.out.print(view.addSellerString[1]);
        Assert.assertEquals(stringTestAddSellerString[1], output.toString());
    }

    @Test
    public void testAddSellerString2() throws IOException {
        view = new View();
        System.out.print(view.addSellerString[2]);
        Assert.assertEquals(stringTestAddSellerString[2], output.toString());
    }



    @Test
    public void testAddProductString0() throws IOException {
        view = new View();
        System.out.print(view.addProductString[0]);
        Assert.assertEquals(stringTesAddProductString[0], output.toString());
    }

    @Test
    public void testAddProductString1() throws IOException {
        view = new View();
        System.out.print(view.addProductString[1]);
        Assert.assertEquals(stringTesAddProductString[1], output.toString());
    }

    @Test
    public void testAddProductString2() throws IOException {
        view = new View();
        System.out.print(view.addProductString[2]);
        Assert.assertEquals(stringTesAddProductString[2], output.toString());
    }

    @Test
    public void testAddProductString3() throws IOException {
        view = new View();
        System.out.print(view.addProductString[3]);
        Assert.assertEquals(stringTesAddProductString[3], output.toString());
    }



    @Test
    public void testAddBuyerString0() throws IOException {
        view = new View();
        System.out.print(view.addBuyerString[0]);
        Assert.assertEquals(stringTestAddBuyerString[0], output.toString());
    }

    @Test
    public void testAddBuyerString1() throws IOException {
        view = new View();
        System.out.print(view.addBuyerString[1]);
        Assert.assertEquals(stringTestAddBuyerString[1], output.toString());
    }

    @Test
    public void testAdBuyerString2() throws IOException {
        view = new View();
        System.out.print(view.addBuyerString[2]);
        Assert.assertEquals(stringTestAddBuyerString[2], output.toString());
    }


    @Test
    public void testAddBidString0() throws IOException {
        view = new View();
        System.out.print(view.addBidString[0]);
        Assert.assertEquals(stringTesAddBidString[0], output.toString());
    }

    @Test
    public void testAddBidString1() throws IOException {
        view = new View();
        System.out.print(view.addBidString[1]);
        Assert.assertEquals(stringTesAddBidString[1], output.toString());
    }

    @Test
    public void testAddBidString2() throws IOException {
        view = new View();
        System.out.print(view.addBidString[2]);
        Assert.assertEquals(stringTesAddBidString[2], output.toString());
    }

    @Test
    public void testAddBidString3() throws IOException {
        view = new View();
        System.out.print(view.addBidString[3]);
        Assert.assertEquals(stringTesAddBidString[3], output.toString());
    }

    @Test
    public void testAddBidString4() throws IOException {
        view = new View();
        System.out.print(view.addBidString[4]);
        Assert.assertEquals(stringTesAddBidString[4], output.toString());
    }

    @Test
    public void testViewActions() throws IOException {
        view = new View();
        System.out.print(view.viewActions);
        Assert.assertEquals(stringViewActions, output.toString());
    }

    @Test
    public void testAddSellerStringNegative() {
        view = new View();
        System.out.println(view.addSellerString[0]);
        Assert.assertNotEquals(stringNegative, output.toString());
    }

    @Test
    public void testAddProductStringNegative() {
        view = new View();
        System.out.println(view.addProductString[1]);
        Assert.assertNotEquals(stringNegative, output.toString());
    }

    @Test
    public void testAddBuyerStringNegative() {
        view = new View();
        System.out.println(view.addBuyerString[2]);
        Assert.assertNotEquals(stringNegative, output.toString());
    }

    @Test
    public void testAddBidStringNegative() {
        view = new View();
        System.out.println(view.addBidString[3]);
        Assert.assertNotEquals(stringNegative, output.toString());
    }

    @Test
    public void testPrintTableDataPositive() {
        view = new View();
        view.printTableData(listQueryP, tableName, columnNames, format);
        Assert.assertNotEquals(stringNegative, output.toString());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPrintTableDataListNegative() {
        view = new View();
        view.printTableData(listQueryN, tableName, columnNames, format);
    }

}