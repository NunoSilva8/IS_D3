package book;

import entities.Client;
import entities.Currency;
import entities.Manager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.DataBaseUtils.*;

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
     * @param client
     * @return
     */
    @POST
    @Path("/add-client")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addClient(Client client){
        /*
            1. If manager não existe... return erro
            2. Else...
            2.1. Add client
            2.2. Return success
         */
        return "Cliente criado com sucesso";
    }

    @GET
    @Path("/get-clients")
    public String getClients(){
        List<Client> clients = new ArrayList<>();
        return "Lista de Clientes";
    }

    /**
     * in Euros
     * @return
     */
    @GET
    @Path("/get-client-credit")
    public String getClientCredit(){
        String clientName = "HARDCODE";
        Long clientCredit = 123L;
        return  "O crédito do cliente " + clientName + "é de " + clientCredit;
    }

    @GET
    @Path("/get-client-payments")
    public String getClientPayments(){
        String clientName = "HARDCODE";
        Long clientPayment = 123L;
        return "O pagamento do cliente " + clientName + "é de " + clientPayment;
    }

    @GET
    @Path("/get-client-balance")
    public String getClientBalance(){
        String clientName = "HARDCODE";
        Long clientBalance = 123L;
        return "O balance do cliente " + clientName + "é de " + clientBalance;
    }

    @GET
    @Path("/get-clients-credit")
    public String getClientsCredit(){
        Long clientsCredit = 123L;
        return "A soma dos créditos dos clientes é de " + clientsCredit;
    }

    @GET
    @Path("/get-clients-payments")
    public String getClientsPayments(){
        Long clientsPayments = 123L;
        return "A soma dos pagamentos dos clientes é de " + clientsPayments;
    }

    @GET
    @Path("/get-clients-balance")
    public String getClientsBalance(){
        Long clientsBalance = 123L;
        return "A soma dos balances dos clientes é de " + clientsBalance;
    }

    @GET
    @Path("/get-client-bill-last-month")
    public String getClientBillLastMonth(){
        String clientName = "HARDCODE";
        Long clientBill = 123L;
        return "A conta do clientes é de " + clientName + " durante o último mês é de " + clientBill;
    }

    @GET
    @Path("/get-clients-no-payments-last-two-months")
    public String getClientsNoPaymentsLastTwoMonths(){
        return "Lista de Clientes sem pagamentos nos últimos 2 meses";
    }

    @GET
    @Path("/get-client-in-most-debt")
    public String getClientMostDebt(){
        String clientName = "HARDCODE";
        return "O cliente com maior dívida é o cliente " + clientName;
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
         /*
            0? Verificar se managerName existe?

            1. Add manager
            2. Return success
         */
        return "Manager criado com sucesso";
    }

    @GET
    @Path("/get-managers")
    public String getManagers(){
        List<Manager> managers = new ArrayList<>();

        return "getManagers";
    }

    @GET
    @Path("/get-manager-most-revenue")
    public String getManagersMostRevenue(){
        List<Manager> managers = new ArrayList<>();

        return "Manager com mais revenue.";
    }

    /******CURRENCY SECTION******/

    /**
     *
     * @param currency
     * @return
     */
    @POST
    @Path("/add-currency")

    @Consumes(MediaType.APPLICATION_JSON)
    public String addCurrency(Currency currency){
         /*
            1. If currencyName existe => return erro
            2. Else...
            2.1. Add currency
            2.2. Return success
         */
        return "Currency criada com sucesso";
    }

    @GET
    @Path("/get-currencies")
    public String getCurrencies(){
        List<Currency> currencies = new ArrayList<>();
        return "Lista de currencies.";
    }
}
