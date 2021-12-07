package controllers;

import java.util.List;

/**
 * Rest Controller For Managers
 */

@Path("/currency")
@Produces(MediaType.APPLICATION.JSON)
public class CurrencyController {


    public CurrencyController() {
    }

    /**
     * add currency method
     * @param managerName
     * @return
     */
    @POST
    @Path("/add-currency")
    public String addCurrency(String currencyName, Double exchangeToEuro){
         /*
            1. If currencyName existe => return erro
            2. Else...
            2.1. Add currency
            2.2. Return success
         */
    }

    @GET
    @Path("/get-currencies")
    public List<String> getCurrencies(){
        return null;
    }
}

