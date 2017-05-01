package api.v1.Models;

import net.minidev.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name="Recipes")

public class Recipe {
    @Id
    @Column( name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "name")
    private final String name;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "tasks")
    private JSONObject tasks;

    public JSONObject getTasks() {
        return tasks;
    }

    @Column(name = "ingredients")
    private JSONObject ingredients;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private long duration;

    @Column(name = "icon_image")
    private String icon_image;

    public Recipe(String name, String difficulty, JSONObject tasks, JSONObject ingredients, String description, long duration, String icon_image) {
        this.name = name;
        this.difficulty = difficulty;
        this.tasks = tasks;
        this.ingredients = ingredients;
        this.description = description;
        this.duration = duration;
        this.icon_image = icon_image;
    }


    public long getId() {
        return id;
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

    public void setTasks(JSONObject tasks) {
        this.tasks = tasks;
    }

    public JSONObject getIngredients() {
        return ingredients;
    }

    public void setIngredients(JSONObject ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
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
        if (tasks != null ? !tasks.equals(recipe.tasks) : recipe.tasks != null) return false;
        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        return icon_image != null ? icon_image.equals(recipe.icon_image) : recipe.icon_image == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (icon_image != null ? icon_image.hashCode() : 0);
        return result;
    }
}
