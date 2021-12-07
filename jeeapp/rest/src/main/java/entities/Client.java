package entities;

import java.sql.Date;
import java.util.List;

public class Client{

    private Integer id;
    private String name;
    private Long credits;
    private List<Payment> payments;
    private Manager manager;

    public Client(String name) {
        this.name = name;
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

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Retorno addPayment(Payment payment){
        if(payment.getDate().after(new Date(System.currentTimeMillis()))){

        }
        else{
            if(!payment.getDebit())
                this.credits += payment.getCurrency().getToEuro() * payment.getAmount();
            else
                if(this.getCredits() > payment.getCurrency().getToEuro() * payment.getAmount())
                    this.credits -= payment.getCurrency().getToEuro() * payment.getAmount();
                else
                    return new Retorno(false, "The user with the id " + this.getId() +
                            "does not have enough credits!");
        }

        this.payments.add(payment);

        return new Retorno(true, "Operation successfull");
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", payments=" + payments +
                '}';
    }
}