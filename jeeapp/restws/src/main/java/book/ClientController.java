package book;
import entities.Client;
import entities.Retorno;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientController {

    public ClientController() {
    }

    /**
     * add manager method
     * @param managerId
     * @param clientName
     * @return
     */
    @POST
    @Path("/add-client")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addClient(Integer managerId, String clientName){
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
    public Retorno getClients(){
        return new Retorno(true, "Lista de Clientes", new ArrayList<>(), new ArrayList<>());
    }

    /**
     * in Euros
     * @return
     */
    @GET
    @Path("/get-client-credit")
    public Retorno getClientCredit(){
        String clientName = "HARDCODE";
        Long clientCredit = 123L;
        return new Retorno(true, "O crédito do cliente " + clientName + "é de " + clientCredit);
    }

    @GET
    @Path("/get-client-payments")
    public Retorno getClientPayments(){
        String clientName = "HARDCODE";
        Long clientPayment = 123L;
        return new Retorno(true, "O pagamento do cliente " + clientName + "é de " + clientPayment);
    }

    @GET
    @Path("/get-client-balance")
    public Retorno getClientBalance(){
        String clientName = "HARDCODE";
        Long clientBalance = 123L;
        return new Retorno(true, "O balance do cliente " + clientName + "é de " + clientBalance);
    }

    @GET
    @Path("/get-clients-credit")
    public Retorno getClientsCredit(){
        Long clientsCredit = 123L;
        return new Retorno(true, "A soma dos créditos dos clientes é de " + clientsCredit);
    }

    @GET
    @Path("/get-clients-payments")
    public Retorno getClientsPayments(){
        Long clientsPayments = 123L;
        return new Retorno(true, "A soma dos pagamentos dos clientes é de " + clientsPayments);
    }

    @GET
    @Path("/get-clients-balance")
    public Retorno getClientsBalance(){
        Long clientsBalance = 123L;
        return new Retorno(true, "A soma dos balances dos clientes é de " + clientsBalance);
    }

    @GET
    @Path("/get-client-bill-last-month")
    public Retorno getClientBillLastMonth(){
        String clientName = "HARDCODE";
        Long clientBill = 123L;
        return new Retorno(true, "A conta do clientes é de " + clientName + " durante o último mês é de " + clientBill);
    }

    @GET
    @Path("/get-clients-no-payments-last-two-months")
    public Retorno getClientsNoPaymentsLastTwoMonths(){
        return new Retorno(true, "Lista de Clientes sem pagamentos nos últimos 2 meses", new ArrayList<>(), new ArrayList<>());
    }

    @GET
    @Path("/get-client-in-most-debt")
    public Retorno getClientMostDebt(){
        String clientName = "HARDCODE";
        return new Retorno(true, "O cliente com maior dívida é o cliente " + clientName);
    }

}