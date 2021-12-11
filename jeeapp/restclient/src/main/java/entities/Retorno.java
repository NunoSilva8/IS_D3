package entities;

import java.util.ArrayList;
import java.util.List;

public class Retorno {

    private static final long serialVersionUID = 1L;

    public Boolean isSuccess;
    public String message;
    public List<Client> clients;
    public List<Manager> managers;
    public List<Currency> currencies;

    public Retorno(){
    }

    public Retorno(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.clients = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public Retorno(Boolean isSuccess, String message, List<Currency> currencies) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.clients = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.currencies = currencies;
    }

    public Retorno(Boolean isSuccess, String message, List<Client> clients, List<Manager> managers) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.clients = clients;
        this.managers = managers;
    }


    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "Retorno{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}
