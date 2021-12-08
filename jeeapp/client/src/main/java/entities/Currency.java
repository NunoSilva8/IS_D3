package entities;

public class Currency {

    private Integer id;
    private String name;
    private Long toEuro;

    public Currency(Integer id, String name, Long toEuro) {
        this.id = id;
        this.name = name;
        this.toEuro = toEuro;
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

    public Long getToEuro() {
        return toEuro;
    }

    public void setToEuro(Long toEuro) {
        this.toEuro = toEuro;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", toEuro=" + toEuro +
                '}';
    }
}