package entities;

import java.sql.Date;

public class Payment{

    private Integer id;
    private Client client;
    private Date date;
    private Currency currency;
    private Long amount;
    private Boolean isDebit;

    public Payment(Integer id, Client client, Date date, Currency currency, Long amount, Boolean isDebit) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.currency = currency;
        this.amount = amount;
        this.isDebit = isDebit;
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
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Boolean getDebit() {
        return isDebit;
    }

    public void setDebit(Boolean debit) {
        isDebit = debit;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", client=" + client +
                ", date=" + date +
                ", currency=" + currency +
                ", amount=" + amount +
                ", isDebit=" + isDebit +
                '}';
    }
}