package entities;

import java.util.List;

public class Manager{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Long revenue;
    private List<Client> clients;

    public Manager(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.revenue = 0L;
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

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Retorno addClient(Client client){
        //TODO: provavelmente vai ser preciso verificar isto por id e não por objeto
        if(clients.contains(client)){
            return new Retorno(false, "O cliente já pertence ao Manager de id: " + this.getId());
        }
        else{
            clients.add(client);
            return new Retorno(true, "O cliente pertence agora ao manager de id: " + this.getId());
        }
    }

    public Retorno removeClient(Client client){
        //TODO: provavelmente vai ser preciso verificar isto por id e não por objeto
        if(!clients.contains(client)){
            return new Retorno(false, "O cliente não pertence ao Manager de id " + this.getId());
        }
        else{
            clients.remove(client);
            return new Retorno(true, "O cliente deixou de pertencer ao manager de id: " + this.getId());
        }
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name='" + name + '\'' +
                ", revenue=" + revenue +
                ", clients=" + clients +
                '}';
    }
}