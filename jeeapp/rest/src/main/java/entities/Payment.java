package entities;

import java.sql.Date;

public class Payment{

    private Integer id;
    private Client client;
    private Date date;
    private Currency currency;
    private Long amount;

    public Payment(Integer id, Client client, Date date) {
        this.id = id;
        this.client = client;
        this.date = date;
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

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", client=" + client.getName() +
                ", date=" + date +
                '}';
    }
}