package api.v1.Models;

/**
 * Created by cristi on 5/5/17.
 */
public class Ingredient {
    private int id;
    private String name;
    private int quantity;
    private String unit;

    public Ingredient(){}

    public Ingredient(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return unit != null ? unit.equals(that.unit) : that.unit == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
