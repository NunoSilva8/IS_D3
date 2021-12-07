

@Path("/client")
@Produces(MediaType.APPLICATION.JSON)
public class ControllerController {

    public ControllerController() {
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
    }

    @GET("/get-clients")
    public List<String> getClients(){
        return null;
    }

    /**
     * in Euros
     * @return
     */
    @GET("/get-client-credit")
    public Double getClientCredit(){
        return null;
    }

    @GET("/get-client-payments")
    public Double getClientPayments(){
        return null;
    }

    @GET("/get-client-balance")
    public Double getClientBalance(){
        return null;
    }

    @GET("/get-client-bill-last-month")
    public Double getClientBillLastMonth(){
        return null;
    }

    @GET("/get-clients-no-payments-last-two-months")
    public List<String> getClientsNoPaymentsLastTwoMonths(){
        return null;
    }

    @GET("/get-client-most-debt")
    public List<String> getClientMostDebt(){
        return null;
    }

}