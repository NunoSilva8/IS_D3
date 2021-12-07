package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/debits")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentController {

    public PaymentController() {
    }

    @GET
    @Path("/get-total-balance")
    public Long getTotalBalance(){
        return null;
    }

    @GET
    @Path("/get-total-payments")
    public Long getTotalPayments(){
        return null;
    }

    @GET
    @Path("/get-total-credits")
    public Long getTotalCredits(){
        return null;
    }
}