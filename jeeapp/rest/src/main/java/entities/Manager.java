package entities;

import java.util.List;

public class Manager{

    private Integer id;
    private String name;
    private List<Client> clients;

    public Manager(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clients=" + clients +
                '}';
    }
}