
/**
 * Rest Controller For Managers
 */
@RestController
@RequestMapping("currency")
public class ManagerController {

    public ManagerController() {
    }

    /**
     * add currency method
     * @param managerName
     * @return
     */
    @PostMapping("add-currency")
    public String addCurrency(String currencyName, Double exchangeToEuro){
         /*
            1. If currencyName existe => return erro
            2. Else...
            2.1. Add currency
            2.2. Return success
         */
    }

    @GetMapping("get-currencies")
    public List<String> getCurrencies(){
        return null;
    }
}

