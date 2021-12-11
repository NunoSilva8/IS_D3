package book;

import entities.Retorno;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
     * add currency method
     * @param currencyName
     * @param exchangeToEuro
     * @return
     */
    @POST
    @Path("/add-currency")
    public Retorno addCurrency(String currencyName, Long exchangeToEuro){
         /*
            1. If currencyName existe => return erro
            2. Else...
            2.1. Add currency
            2.2. Return success
         */
        return new Retorno(true, "Currency criada com sucesso");
    }

    @GET
    @Path("/get-currencies")
    public Retorno getCurrencies(){
        return new Retorno(true, "Lista de currencies.", new ArrayList<>());
    }
}

