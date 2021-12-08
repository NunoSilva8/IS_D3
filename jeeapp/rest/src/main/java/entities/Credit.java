package entities;

import java.sql.Date;

public class Credit {

    private Integer id;
    private Client client;
    private Date deadline;
    private Currency currency;
    private Long amount;

    public Credit(Integer id, Client client, Date deadline, Currency currency, Long amount, Boolean isDebit) {
        this.id = id;
        this.client = client;
        if(!deadline.after(new Date(System.currentTimeMillis())))
            this.deadline = new Date(new Date(System.currentTimeMillis()).getTime() + (1000*60*60*24)); //Data de amanhã
        else
            this.deadline = deadline;
        this.currency = currency;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return deadline;
    }

    public void setDate(Date deadline) {
        this.deadline = deadline;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", client=" + client +
                ", deadline=" + deadline +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}