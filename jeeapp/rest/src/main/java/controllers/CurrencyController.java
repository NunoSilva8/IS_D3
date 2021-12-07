package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest Controller For Managers
 */

@Path("/currency")
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyController {


    public CurrencyController() {
    }

    /**
     * add currency method
     * @param currencyName
     * @param exchangeToEuro
     * @return
     */
    @POST
    @Path("/add-currency")
    public String addCurrency(String currencyName, Long exchangeToEuro){
         /*
            1. If currencyName existe => return erro
            2. Else...
            2.1. Add currency
            2.2. Return success
         */
        return null;
    }

    @GET
    @Path("/get-currencies")
    public List<String> getCurrencies(){
        return null;
    }
}

