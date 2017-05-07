package api.v1.Models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Set;

@Entity
@Indexed

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String difficulty;
    private String description;
    private int duration;
    private String icon_image;
    private String uuid;
    private Set images;
    private Set steps;
    private Set ingredients;

    public Recipe(String name, String difficulty, String description, int duration, String icon_image) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.duration = duration;
        this.icon_image = icon_image;
    }

    public Recipe() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public Set getImages() {
        return images;
    }

    public void setImages(Set images) {
        this.images = images;
    }

    public Set getSteps() {
        return steps;
    }

    public void setSteps(Set steps) {
        this.steps = steps;
    }

    public void setIngredients(Set ingredients) {
        this.ingredients = ingredients;
    }

    public Set getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != recipe.id) return false;
        if (duration != recipe.duration) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (difficulty != null ? !difficulty.equals(recipe.difficulty) : recipe.difficulty != null) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        return icon_image != null ? icon_image.equals(recipe.icon_image) : recipe.icon_image == null;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (duration ^ (duration >>> 32));
        result = 31 * result + (icon_image != null ? icon_image.hashCode() : 0);
        return result;
    }
}
