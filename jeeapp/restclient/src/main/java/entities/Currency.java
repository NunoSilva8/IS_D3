package entities;

public class Currency{

    private static final long serialVersionUID = 1L;

    private String name;
    private Long toEuro;

    public Currency(String name, Long toEuro) {
        this.name = name;
        this.toEuro = toEuro;
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
                ", name='" + name + '\'' +
                ", toEuro=" + toEuro +
                '}';
    }
}