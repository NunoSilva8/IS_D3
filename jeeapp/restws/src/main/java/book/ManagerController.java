package book;

import entities.Manager;
import entities.Retorno;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest Controller For Managers
 */

@Path("/manager")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerController {

    public ManagerController() {
    }

    /**
     * add manager method
     * @param managerName
     * @return
     */
    @POST
    @Path("/add-manager")
    @Consumes(MediaType.APPLICATION_JSON)
    public Retorno addManager(String managerName){
         /*
            0? Verificar se managerName existe?

            1. Add manager
            2. Return success
         */
        return new Retorno(true, "Manager criado com sucesso");
    }

    @GET
    @Path("/get-managers")
    public Retorno getManagers(){
        return new Retorno(true, "Lista de managers.", new ArrayList<>(), new ArrayList<>());
    }

    @GET
    @Path("/get-manager-most-revenue")
    public Retorno getManagersMostRevenue(){
        return new Retorno(true, "Manager com mais revenue.", new ArrayList<>(), new ArrayList<>());
    }
}

