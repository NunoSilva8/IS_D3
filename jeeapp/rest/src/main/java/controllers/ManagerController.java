package controllers;

import entities.Manager;
import entities.Retorno;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public Retorno addManager(String managerName){
         /*
            0? Verificar se managerName existe?

            1. Add manager
            2. Return success
         */
        return null;
    }

    @GET
    @Path("/get-managers")
    public List<Manager> getManagers(){
        return null;
    }

    @GET
    @Path("/get-managers-most-revenue")
    public List<Manager> getManagersMostRevenue(){
        return null;
    }
}

