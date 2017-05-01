package api.v1.Models;

import javax.persistence.*;

/**
 * Created by cristi on 4/30/17.
 */
@Entity
@Table(name="Phone")

public class Phone {

    @Id
    @Column( name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "review")
    private String review;

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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (id != phone.id) return false;
        if (name != null ? !name.equals(phone.name) : phone.name != null) return false;
        return review != null ? review.equals(phone.review) : phone.review == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        return result;
    }
}
