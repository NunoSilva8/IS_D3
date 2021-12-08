package entities;

import java.sql.Date;


public class Payment {

    private Integer id;
    private Date date;
    private Currency currency;
    private Long amount;

    public Payment(Currency currency, Long amount) {
        this.date = new Date(System.currentTimeMillis());
        this.currency = currency;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", date=" + date +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}