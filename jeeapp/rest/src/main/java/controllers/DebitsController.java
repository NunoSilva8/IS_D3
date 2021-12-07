package controllers;

@Path("/debits")
@Produces(MediaType.APPLICATION.JSON)
public class DebitsController {

    public DebitsController() {
    }

    @GET
    @Path("/get-total-balance")
    public Double getTotalBalance(){
        return null;
    }

    @GET
    @Path("/get-total-payments")
    public Double getTotalPayments(){
        return null;
    }

    @GET
    @Path("/get-total-credits")
    public Double getTotalCredits(){
        return null;
    }
}