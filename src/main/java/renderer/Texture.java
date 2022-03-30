package renderer;

public class Texture {
    private String filePath;
    private int texID;
    private int width, height;

    public Texture(String filePath) {
        this.init(filePath);
    }

    private void init(String filePath) {
        this.filePath = filePath;
    }


}
