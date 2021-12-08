package controllers;
import entities.Client;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public String addClient(Integer managerId, String clientName){
        /*
            1. If manager não existe... return erro
            2. Else...
            2.1. Add client
            2.2. Return success
         */
        return null;
    }

    @GET
    @Path("/get-clients")
    public List<Client> getClients(){
        return null;
    }

    /**
     * in Euros
     * @return
     */
    @GET
    @Path("/get-client-credit")
    public Long getClientCredit(){
        return null;
    }

    @GET
    @Path("/get-client-payments")
    public Long getClientPayments(){
        return null;
    }

    @GET
    @Path("/get-client-balance")
    public Long getClientBalance(){
        return null;
    }

    @GET
    @Path("/get-client-bill-last-month")
    public Long getClientBillLastMonth(){
        return null;
    }

    @GET
    @Path("/get-clients-no-payments-last-two-months")
    public List<String> getClientsNoPaymentsLastTwoMonths(){
        return null;
    }

    @GET
    @Path("/get-client-most-debt")
    public List<String> getClientMostDebt(){
        return null;
    }

}