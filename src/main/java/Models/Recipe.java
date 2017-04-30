package Models;

public class Recipe {
    private final long id;
    private final String content;

    public Recipe(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
