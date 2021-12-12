package entities;

public class Client{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Double balance;
    private Double sumPayments;
    private Double sumCredits;
    private Manager manager;

    public Client(String name, Manager manager) {
        this.name = name;
        this.balance = 0.0;
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

    public Double getBalance() { return balance; }

    public void setBalance(Double balance) { this.balance = balance; }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Double getSumPayments() {
        return sumPayments;
    }

    public Double getSumCredits() {
        return sumCredits;
    }

    public void addCredit(Double amountInEuros){
        this.balance += amountInEuros;
        this.sumCredits += amountInEuros;
    }

    public void addPayment(Double amountInEuros){
        this.balance -= amountInEuros;
        this.sumPayments += amountInEuros;
    }

    @Override
    public String toString() {
        return "{id= " + id +
                ", name='" + name +
                ", balance=" + balance +
                ", payments=" + sumPayments +
                ", credits=" + sumCredits +
                '}';
    }
}