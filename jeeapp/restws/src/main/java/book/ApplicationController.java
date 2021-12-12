package book;

import entities.Client;
import entities.Currency;
import entities.Manager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.DataBaseUtils.*;
import static database.InsertQueries.*;
import static database.DataBaseQueries.*;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationController {

    private Connection dbConnection;

    public ApplicationController() throws SQLException {
        dbConnection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    /******CLIENT SECTION******/

    /**
     *
     * @param inputs
     * @return
     */
    @POST
    @Path("/add-client")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addClient(String inputs){
        String[] splitStr = inputs.split("\\s+");

        try{
            PreparedStatement ps = dbConnection.prepareStatement(INSERT_CLIENT);
            ps.setString(1, splitStr[0]);
            ps.setDouble(2, 0.0);
            ps.setString(3, splitStr[1]);
            ps.setDouble(4, 0.0);
            ps.setDouble(5, 0.0);
            ps.setDouble(6, 0.0);
            ps.setDouble(7, 0.0);

            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao criar cliente.";
        }

        return "Cliente criado com sucesso.";
    }

    @GET
    @Path("/get-clients")
    public String getClients(){
        String clientsAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_ALL_CLIENTS);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientsAsString += "ClientId: " +
                rSet.getInt(1) + ", ClientName: " +
                rSet.getString(2) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar clientes.";
        }

        return clientsAsString;
    }

    /**
     * in Euros
     * @return
     */
    @GET
    @Path("/get-client-credit")
    public String getClientCredit(@QueryParam("clientId") Integer clientId){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_CLIENT_CREDITS_BY_ID);
            ps.setInt(1, clientId);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientId: " +
                        rSet.getInt(1) + ", ClientName: " +
                        rSet.getString(2) + ", ClientCredit: " +
                        rSet.getDouble(3) +"\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o cliente.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-client-payments")
    public String getClientPayments(@QueryParam("clientId") Integer clientId){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_CLIENT_PAYMENTS_BY_ID);
            ps.setInt(1, clientId);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientId: " +
                        rSet.getInt(1) + ", ClientName: " +
                        rSet.getString(2) + ", ClientPayments: " +
                        rSet.getDouble(3) +"\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o cliente.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-client-balance")
    public String getClientBalance(@QueryParam("clientId") Integer clientId){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_CLIENT_BALANCE_BY_ID);
            ps.setInt(1, clientId);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientId: " +
                        rSet.getInt(1) + ", ClientName: " +
                        rSet.getString(2) + ", ClientBalance: " +
                        rSet.getDouble(3) +"\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o cliente.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-clients-credit")
    public String getClientsCredit(){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_SUM_CLIENT_CREDITS);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientsTotalCredits: " +
                        rSet.getString(1) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao aceder à soma dos créditos dos clientes.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-clients-payments")
    public String getClientsPayments(){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_SUM_CLIENT_PAYMENTS);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientsTotalPayments: " +
                        rSet.getString(1) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao aceder à soma dos pagamentos dos clientes.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-clients-balance")
    public String getClientsBalance(){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_SUM_CLIENT_BALANCES);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientsTotalPayments: " +
                        rSet.getString(1) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao aceder à soma dos balances dos clientes.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-client-bill-last-month")
    public String getClientBillLastMonth(@QueryParam("clientId") Integer clientId){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_CLIENT_LAST_MONTH_BILL);
            ps.setInt(1, clientId);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientId: " +
                        rSet.getInt(1) + ", ClientName: " +
                        rSet.getString(2) + ", ClientBalanceLastMonth: " +
                        rSet.getDouble(3) +"\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o cliente.";
        }

        return clientAsString;
    }


    @GET
    @Path("/get-clients-no-payments-last-two-months")
    public String getClientsNoPaymentsLastTwoMonths(){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_CLIENTS_NO_PAYMENTS_LAST_TWO_MONTHS);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientId: " +
                        rSet.getInt(1) + ", ClientName: " +
                        rSet.getString(2) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o cliente.";
        }

        return clientAsString;
    }

    @GET
    @Path("/get-client-in-most-debt")
    public String getClientMostDebt(){
        String clientAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_CLIENT_MIN_BALANCE);
            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                clientAsString += "ClientId: " +
                        rSet.getInt(1) + ", ClientName: " +
                        rSet.getString(2) + ", ClientBalance: " +
                        rSet.getDouble(3) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o cliente.";
        }

        return clientAsString;
    }

    /******MANAGER SECTION******/

    /**
     * add manager method
     * @param managerName
     * @return
     */
    @POST
    @Path("/add-manager")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addManager(String managerName){
        try{
            PreparedStatement ps = dbConnection.prepareStatement(INSERT_MANAGER);
            ps.setString(1, managerName);

            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
            return "[DATABASE ERROR] Erro ao criar manager.";
        }

        return "Manager criado com sucesso";
    }

    @GET
    @Path("/get-managers")
    public String getManagers(){
        String managersAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_ALL_MANAGERS);

            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                managersAsString += "ManagerId: " +
                        rSet.getInt(1) + ", ManagerName: " +
                        rSet.getString(2) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar managers.";
        }

        return managersAsString;
    }

    @GET
    @Path("/get-manager-most-revenue")
    public String getManagerMostRevenue(){
        String managerAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_MANAGER_MOST_REVENUE);

            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                managerAsString += "ManagerId: " +
                        rSet.getInt(1) + ", ManagerName: " +
                        rSet.getString(2) + ", ManagerRevenue: " +
                        rSet.getDouble(3) +"\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar o manager com maiores ganhos.";
        }

        return managerAsString;
    }

    /******CURRENCY SECTION******/

    /**
     *
     * @param inputs
     * @return
     */
    @POST
    @Path("/add-currency")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addCurrency(String inputs){
        String[] splitStr = inputs.split("\\s+");
        Double toEuro = Double.parseDouble(splitStr[1]);
        
        try{
            PreparedStatement ps = dbConnection.prepareStatement(INSERT_CURRENCY);
            ps.setString(1, splitStr[0]);
            ps.setDouble(2, toEuro);

            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao criar moeda.";
        }

        return "Currency criada com sucesso";
    }

    @GET
    @Path("/get-currencies")
    public String getCurrencies(){
        String currenciesAsString = "";

        try{
            PreparedStatement ps = dbConnection.prepareStatement(SELECT_ALL_CURRENCIES);

            ResultSet rSet = ps.executeQuery();

            while(rSet.next()){
                currenciesAsString += "CurrencyName: " +
                        rSet.getString(1) + ", ExchangeToEuro: " +
                        rSet.getDouble(2) + "\n";
            }

            ps.close();
        }catch(SQLException e){
            return "[DATABASE ERROR] Erro ao procurar moedas.";
        }

        return currenciesAsString;
    }
}
