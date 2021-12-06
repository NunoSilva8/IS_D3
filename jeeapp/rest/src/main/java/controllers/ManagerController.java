package controllers;

import java.util.List;

/**
 * Rest Controller For Managers
 */
@RestController
@RequestMapping("manager")
public class ManagerController {

    public ManagerController() {
    }

    /**
     * add manager method
     * @param managerName
     * @return
     */
    @PostMapping("add-manager")
    public String addManager(String managerName){
         /*
            0? Verificar se managerName existe?

            1. Add manager
            2. Return success
         */
    }

    @GetMapping("get-managers")
    public List<String> getManagers(){
        return null;
    }

    @GetMapping("get-managers-most-revenue")
    public List<String> getManagersMostRevenue(){
        return null;
    }
}

