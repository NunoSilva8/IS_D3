package entities;

public class Client {

    private Integer id;
    private String name;
    private Long balance;
    private Long sumPayments;
    private Long sumCredits;

    public Client(String name) {
        this.name = name;
        this.balance = 0L;
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