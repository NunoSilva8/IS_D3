
@RestController
@RequestMapping("client")
public class ControllerController {

    public ControllerController() {
    }

    /**
     * add manager method
     * @param managerId
     * @param clientName
     * @return
     */
    @PostMapping("add-client")
    public String addClient(Integer managerId, String clientName){
        /*
            1. If manager não existe... return erro
            2. Else...
            2.1. Add client
            2.2. Return success
         */
    }

    @GetMapping("get-clients")
    public List<String> getClients(){
        return null;
    }

    /**
     * in Euros
     * @return
     */
    @GetMapping("get-client-credit")
    public Double getClientCredit(){
        return null;
    }

    @GetMapping("get-client-payments")
    public Double getClientPayments(){
        return null;
    }

    @GetMapping("get-client-balance")
    public Double getClientBalance(){
        return null;
    }

    @GetMapping("get-client-bill-last-month")
    public Double getClientBillLastMonth(){
        return null;
    }

    @GetMapping("get-clients-no-payments-last-two-months")
    public List<String> getClientsNoPaymentsLastTwoMonths(){
        return null;
    }

    @GetMapping("get-client-most-debt")
    public List<String> getClientMostDebt(){
        return null;
    }

}