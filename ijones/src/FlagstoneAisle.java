/**
 * Created by anatolii on 28.06.16.
 */

public class FlagstoneAisle {
    private int width;
    private int height;
    private String[] letters;

    public FlagstoneAisle(int width, int height) {
        this.width = width;
        this.height = height;
        this.letters = new String[height];
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public String[] getLetters() { return letters; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }

    public void setLetters(String[] letters) { this.letters = letters;}
}
