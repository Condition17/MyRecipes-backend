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
}
