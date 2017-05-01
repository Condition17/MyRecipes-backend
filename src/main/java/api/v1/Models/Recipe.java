package api.v1.Models;

import javax.persistence.*;

@Entity
@Table(name= "recipes")

public class Recipe {
    @Id
    @Column( name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private long duration;

    @Column(name = "icon_image")
    private String icon_image;

    @Column(name = "uuid")
    private String uuid;

    public Recipe(String name, String difficulty, String description, long duration, String icon_image) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        return icon_image != null ? icon_image.equals(recipe.icon_image) : recipe.icon_image == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (icon_image != null ? icon_image.hashCode() : 0);
        return result;
    }
}
