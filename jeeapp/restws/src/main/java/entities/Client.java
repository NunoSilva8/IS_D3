package entities;

public class Client{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Long balance;
    private Long sumPayments;
    private Long sumCredits;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Long getSumPayments() {
        return sumPayments;
    }

    public Long getSumCredits() {
        return sumCredits;
    }

    public void addCredit(Long amountInEuros){
        this.balance += amountInEuros;
        this.sumCredits += amountInEuros;
    }

    public void addPayment(Long amountInEuros){
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