package controllers;

@RestController
@RequestMapping("debits")
public class DebitsController {

    public DebitsController() {
    }

    @GetMapping("get-total-balance")
    public Double getTotalBalance(){
        return null;
    }

    @GetMapping("get-total-payments")
    public Double getTotalPayments(){
        return null;
    }

    @GetMapping("get-total-credits")
    public Double getTotalCredits(){
        return null;
    }
}