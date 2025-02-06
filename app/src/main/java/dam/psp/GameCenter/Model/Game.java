package dam.psp.GameCenter.Model;

public class Game {

    private String image;
    private String name;
    private String url;
    boolean fav;

    public Game(String image, String name, String url, boolean fav) {
        this.image = image;
        this.name = name;
        this.url = url;
        this.fav = fav;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
    
    

}
