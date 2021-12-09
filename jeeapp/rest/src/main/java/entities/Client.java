package entities;

import java.sql.Date;
import java.util.List;

public class Client{

    private Integer id;
    private String name;
    private Long balance;
    private List<Payment> payments;
    private List<Credit> credits;
    private Manager manager;

    public Client(String name, Manager manager) {
        this.name = name;
        this.balance = 0L;
        this.manager = manager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBalance() { return balance; }

    public void setBalance(Long balance) { this.balance = balance; }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Retorno addCredit(Credit credit){
        this.balance += credit.getCurrency().getToEuro() * credit.getAmount();
        return new Retorno(true, "Operation successfull");
    }

    public Retorno addPayment(Payment payment){
        this.balance -= payment.getCurrency().getToEuro() * payment.getAmount();
        return new Retorno(true, "Operation successfull");
    }

    @Override
    public String toString() {
        return "Client{" +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", payments=" + payments +
                ", credits=" + credits +
                '}';
    }
}