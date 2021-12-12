package book;

import entities.Currency;
import entities.Retorno;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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

