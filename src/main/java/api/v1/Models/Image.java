package api.v1.Models;

/**
 * Created by cristi on 5/4/17.
 */
public class Image {

    private int id;
    private String url;

    public Image(){}
    public Image(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (id != image.id) return false;
        return url != null ? url.equals(image.url) : image.url == null;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

}
