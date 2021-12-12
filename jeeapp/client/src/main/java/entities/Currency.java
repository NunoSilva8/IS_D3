package entities;

public class Currency {

    private String name;
    private Double toEuro;

    public Currency(String name, Double toEuro) {
        this.name = name;
        this.toEuro = toEuro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getToEuro() {
        return toEuro;
    }

    public void setToEuro(Double toEuro) {
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