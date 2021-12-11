package book;

import entities.Manager;

import javax.ws.rs.*;
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
    @Consumes(MediaType.APPLICATION_JSON)
    public String addManager(String managerName){
         /*
            0? Verificar se managerName existe?

            1. Add manager
            2. Return success
         */
        return "Correu a função AddManager";
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

