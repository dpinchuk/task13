import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller extends Auction {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private View view = new View();
    private PreparedStatement preparedStatement;
    private ResultSet resultQuery;
    Connection connection;
    Constructor constructor;
    Class[] parameters;
    List<String> list;

    public Controller(Connection connection) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        this.connection = connection;
        this.initLists();
    }

    private void initLists() throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        sellersList.clear();
        productsList.clear();
        buyersList.clear();
        bidsList.clear();
        this.addObjectToList("sellers", Seller.class, sellersList);
        this.addObjectToList("products", Product.class, productsList);
        this.addObjectToList("buyers", Buyer.class, buyersList);
        this.addObjectToList("bids", Bid.class, bidsList);
    }

    public void selectItem() throws IOException, SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String param = "";
        System.out.println(this.view.viewActions);
        while (true) {
            param = this.getParam();
            if (param.equals(EXIT)) {
                System.out.println("\n" + "Exit from <Auction>...");
                break;
            } else {
                this.mainMenu(param);
            }
        }
    }

    private String getParam() throws IOException {
        String param = "";
        while (true) {
            System.out.print("\n" + "Select [Menu Item] -> ");
            param = reader.readLine();
            if (param.equals(EXIT)) {
                System.out.println("\n" + "Exit from current <Menu>...");
                break;
            }
            return param;
        }
        return EXIT;
    }

    private void mainMenu(String action) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        switch (action) {
            case "0":
                break;
            case "1":
                list = new ArrayList<>();
                for (Seller seller : sellersList) {
                    list.add(String.valueOf(seller.getSellerId()) + "|" + seller.getSellerName() + "|" + seller.getSellerLastname());
                }
                this.view.printTableData(list, "sellers", new String[]{"[ID]", "[NAME]", "[LASTNAME]"}, "%4s %12s %16s");
                break;
            case "2":
                list = new ArrayList<>();
                for (Product product : productsList) {
                    list.add(String.valueOf(product.getProductId()) + "|" + product.getProductName() + "|" + product.getProductStartPrice() + "|" + product.getProductSalePrice() + "|" + String.valueOf(product.getIdSeller()));
                }
                this.view.printTableData(list, "products", new String[]{"[ID]", "[PRODUCT NAME]", "[PRICE START]", "[PRICE SALE]", "[SELLER ID]"}, "%4s %20s %19s %18s %17s");
                break;
            case "3":
                list = new ArrayList<>();
                for (Buyer buyer : buyersList) {
                    list.add(String.valueOf(buyer.getBuyerId()) + "|" + buyer.getBuyerName() + "|" + buyer.getBuyerLastname());
                }
                this.view.printTableData(list, "buyers", new String[]{"[ID]", "[NAME]", "[LASTNAME]"}, "%4s %12s %16s");
                break;
            case "4":
                list = new ArrayList<>();
                for (Bid bid : bidsList) {
                    list.add(String.valueOf(bid.getBidId()) + "|" + bid.getBidStep() + "|" + bid.getBidCurrent() + "|" + bid.getBuyerId() + "|" + bid.getProductId());
                }
                this.view.printTableData(list, "bids", new String[]{"[ID]", "[BID STEP]", "[CURRENT]", "[BUYER ID]", "[SELLER ID]"}, "%4s %16s %15s %16s %17s");
                break;
            case "5":
                this.addEntityToDB("sellers", Seller.class, this.view.addSellerString, "?, ?", sellersList);
                break;
            case "6":
                this.addEntityToDB("products", Product.class, this.view.addProductString, "?, ?, ?, ?", productsList);
                break;
            case "7":
                this.addEntityToDB("buyers", Buyer.class, this.view.addBuyerString, "?, ?", buyersList);
                break;
            case "8":
                this.addEntityToDB("bids", Bid.class, this.view.addBidString, "?, ?, ?, ?", bidsList);
                break;
            case "9":
                this.editEntityInDB("sellers", new String[]{"seller_id", "seller_name", "seller_lastname"}, Seller.class, sellersList);
                break;
            case "10":
                this.editEntityInDB("products", new String[]{"product_id", "product_name", "product_start_price", "product_sale_price", "seller_id"}, Product.class, productsList);
                break;
            case "11":
                this.editEntityInDB("buyers", new String[]{"buyer_id", "buyer_name", "buyer_lastname"}, Buyer.class, buyersList);
                break;
            case "12":
                this.editEntityInDB("bids", new String[]{"bid_id", "bid_step", "bid_current", "buyer_id", "product_id"}, Bid.class, bidsList);
                break;
            case "13":
                this.deleteEntityFromDB("sellers", Seller.class, sellersList);
                break;
            case "14":
                this.deleteEntityFromDB("products", Product.class, productsList);
                break;
            case "15":
                this.deleteEntityFromDB("buyers", Buyer.class, buyersList);
                break;
            case "16":
                this.deleteEntityFromDB("bids", Bid.class, bidsList);
                break;
            default:
                break;
        }
    }

    private void addObjectToList(String table, Class modelClass, List list) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        this.preparedStatement = this.connection.prepareStatement("SELECT * FROM " + table + " ORDER BY " + table.substring(0, table.length() - 1) + "_id");
        this.resultQuery = this.preparedStatement.executeQuery();
        int columnCount = this.resultQuery.getMetaData().getColumnCount();
        String string = "";
        while (this.resultQuery.next()) {
            for (int i = 1; i <= columnCount; i++) {
                string += this.resultQuery.getString(i) + "|";
            }
            this.constructor = modelClass.getConstructors()[0];
            this.parameters = constructor.getParameterTypes();
            String[] strings = getData(string);
            list.add(modelClass.getConstructor(parameters).newInstance(strings));
            string = "";
        }
        this.resultQuery.close();
        this.preparedStatement.close();
    }

    private void addEntityToDB(String table, Class modelClass, String[] elements, String params, List list) throws SQLException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("[ADDING]");
        boolean noExit = true;
        this.constructor = modelClass.getConstructors()[0];
        this.parameters = constructor.getParameterTypes();
        String[] strings = new String[parameters.length];
        int rowsCount = list.size() + 1;
        strings[0] = String.valueOf(rowsCount);
        for (int i = 1; i < parameters.length; i++) {
            System.out.print("Input " + elements[i] + " for adding: ");
            if (isNecessaryId(elements[i])) {
                System.out.print("[ ");
                String s = getTableID(elements[i]).substring(0, getTableID(elements[i]).length() - 3) + "s";
                for (String e : this.getEntitiesArray(s)) {
                    System.out.print(e + " ");
                }
                System.out.print("]: ");
            }
            strings[i] = this.reader.readLine();
            if (this.isNecessaryId(elements[i]) || this.isNecessaryPrice(elements[i]) || this.isNecessaryBid(elements[i])) {
                try {
                    int num = Integer.parseInt(strings[i]);
                } catch (Exception e) {
                    noExit = false;
                    break;
                }
            }
            noExit = this.isValidData(table, strings[i]);
            if (!noExit) {
                break;
            }
        }
        if (noExit) {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO " + table + " VALUES (null, " + params + ")");
            for (int i = 1; i < parameters.length; i++) {
                this.preparedStatement.setString(i, strings[i]);
            }
            this.preparedStatement.executeUpdate();
            this.preparedStatement.close();
            this.resultQuery.close();
            list.clear();
            this.addObjectToList(table, modelClass, list);
        } else {
            System.out.println("Error input data. Nothing added.");
        }
    }

    private void deleteEntityFromDB(String table, Class modelClass, List list) throws SQLException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("[DELETING]");
        String id = inputId(table, this.getEntitiesArray(table));
        boolean exit = true;
        if (id.equals("0")) {
            exit = false;
        }
        if (exit) {
            this.preparedStatement = this.connection.prepareStatement("DELETE FROM " + table + " WHERE " + table.substring(0, table.length() - 1) + "_id" + " = ?");
            this.preparedStatement.setString(1, id);
            this.preparedStatement.executeUpdate();
            this.preparedStatement.close();
            list.clear();
            this.addObjectToList(table, modelClass, list);
        }
    }

    private void editEntityInDB(String table, String[] fieldNames, Class modelClass, List list) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("[EDITING]");
        String id = inputId(table, this.getEntitiesArray(table));
        boolean noExit = true;
        if (id.equals("0") || id.equals("")) {
            noExit = false;
        }
        if (noExit) {
            boolean noExitEdit = true;
            String[] newValue = new String[fieldNames.length - 1];
            String qwery = "UPDATE " + table + " SET ";
            for (int i = 1; i < fieldNames.length; i++) {
                if (this.isNecessaryId(fieldNames[i])) {
                    System.out.print("Current [" + fieldNames[i] + "] -> " + this.getEntity(table, fieldNames[i], id) + " All -> " + "[ ");
                    for (String strId : this.getEntitiesArray(fieldNames[i].substring(0, fieldNames[i].length() - 3) + "s")) {
                        System.out.print(strId + " ");
                    }
                    System.out.println("]");
                } else {
                    System.out.println("Current [" + fieldNames[i] + "] -> " + this.getEntity(table, fieldNames[i], id));
                }
                System.out.print("Edit [" + fieldNames[i].toUpperCase() + "]: ");
                newValue[i - 1] = this.reader.readLine();
                if (newValue[i - 1].equals("")) {
                    if (this.isNecessaryPrice(fieldNames[i])) {
                        System.out.println(fieldNames[i] + " is 0");
                        newValue[i - 1] = "0";
                    } else {
                        noExitEdit = false;
                        break;
                    }
                }
                if (newValue[i - 1].equals("0") && this.isNecessaryId(fieldNames[i])) {
                    noExitEdit = false;
                    break;
                }
                if (this.isNecessaryId(fieldNames[i]) || this.isNecessaryPrice(fieldNames[i]) || this.isNecessaryBid(fieldNames[i])) {
                    try {
                        int num = Integer.parseInt(newValue[i - 1]);
                    } catch (Exception e) {
                        noExitEdit = false;
                        break;
                    }
                }
                qwery += fieldNames[i] + " = ?, ";
            }
            if (noExitEdit) {
                qwery = qwery.substring(0, qwery.length() - 2) + " WHERE " + fieldNames[0] + " = " + id;
                this.preparedStatement = this.connection.prepareStatement(qwery);
                for (int i = 0; i < newValue.length; i++) {
                    this.preparedStatement.setString(i + 1, newValue[i]);
                }
                this.preparedStatement.executeUpdate();
                this.preparedStatement.close();
                list.clear();
                this.addObjectToList(table, modelClass, list);
            } else {
                System.out.println("Error input data. Nothing edited.");
            }
        }
    }

    private String[] getEntitiesArray(String table) throws SQLException {
        this.preparedStatement = this.connection.prepareStatement("SELECT " + table.substring(0, table.length() - 1) + "_id" + " FROM " + table + " ORDER BY " + table.substring(0, table.length() - 1) + "_id");
        this.resultQuery = this.preparedStatement.executeQuery();
        String rows = "";
        while (this.resultQuery.next()) {
            rows += this.resultQuery.getString(table.substring(0, table.length() - 1) + "_id") + "|";
        }
        return getData(rows);
    }

    private String getEntity(String table, String fieldName, String id) throws SQLException {
        this.preparedStatement = this.connection.prepareStatement("SELECT " + fieldName + " FROM " + table + " WHERE " + table.substring(0, table.length() - 1) + "_id = \'" + id + "\'");
        this.resultQuery = this.preparedStatement.executeQuery();
        String entity = "";
        while (this.resultQuery.next()) {
            entity = this.resultQuery.getString(fieldName);
        }
        if (this.isNecessaryPrice(fieldName) && (entity == null)) {
            entity = "0";
        }
        if (this.isNecessaryPrice(fieldName) && (entity.equals(""))) {
            entity = "0";
        }
        this.resultQuery.close();
        this.preparedStatement.close();
        return entity;
    }

    private boolean isEntityPresent(String entity, String[] entities) {
        for (String e : entities) {
            if (entity.equals(e)) {
                return true;
            }
        }
        return false;
    }

    private String inputId(String table, String[] allId) throws IOException {
        String id = "";
        while (!this.isEntityPresent(id, allId) && !id.equals("0")) {
            System.out.print("Select record from the list: [ ");
            for (String s : allId) {
                System.out.print(s + " ");
            }
            System.out.println("]");
            System.out.print("Input [" + table.toUpperCase().substring(0, table.length() - 1) + " ID] ('0' -> Exit): ");
            id = this.reader.readLine();
        }
        return id;
    }

    private String getTableID(String string) {
        if (string.toLowerCase().contains("seller_id")) {
            return "seller_id";
        }
        if (string.toLowerCase().contains("product_id")) {
            return "product_id";
        }
        if (string.toLowerCase().contains("buyer_id")) {
            return "buyer_id";
        }
        return "";
    }

    private boolean isNecessaryId(String id) {
        if (id.toLowerCase().contains("seller_id") ||
            id.toLowerCase().contains("product_id") ||
            id.toLowerCase().contains("buyer_id")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNecessaryPrice(String id) {
        if (id.toLowerCase().contains("price")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNecessaryBid(String str) {
        if (str.toLowerCase().contains("step") ||
            str.toLowerCase().contains("current")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidData(String table, String str) {
        if (str.equals("")) {
            return false;
        }
        if (this.isNecessaryId(table) || this.isNecessaryPrice(table) || this.isNecessaryBid(table)) {
            try {
                int num = Integer.parseInt(str);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

}