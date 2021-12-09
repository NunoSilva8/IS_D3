package entities;

import java.sql.Date;


public class Payment {

    private Integer id;
    private Date payDate;
    private Currency currency;
    private Long amount;

    public Payment(Currency currency, Long amount) {
        this.payDate = new Date(System.currentTimeMillis());
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
        return payDate;
    }

    public void setDate(Date payDate) {
        this.payDate = payDate;
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
                ", payDate=" + payDate +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}