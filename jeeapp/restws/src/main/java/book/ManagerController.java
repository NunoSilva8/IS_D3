package book;

import entities.Manager;
import entities.Retorno;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public String addManager(String managerName){
         /*
            0? Verificar se managerName existe?

            1. Add manager
            2. Return success
         */
        return "Manager criado com sucesso";
    }

    @GET
    @Path("/get-managers")
    public Response getManagers(){
        List<Manager> managers = new ArrayList<>();
        managers.add(new Manager(1, "Steve"));
        managers.add(new Manager(2, "Jobs"));
        return Response.ok().entity(managers).build();
    }

    @GET
    @Path("/get-manager-most-revenue")
    public Retorno getManagersMostRevenue(){
        return new Retorno(true, "Manager com mais revenue.", new ArrayList<>(), new ArrayList<>());
    }
}

