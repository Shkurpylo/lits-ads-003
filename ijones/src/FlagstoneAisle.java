/**
 * Created by anatolii on 28.06.16.
 */

public class FlagstoneAisle {
    private int columns;
    private int rows;
    private String[] letters;

    public FlagstoneAisle(int columns, int height) {
        this.columns = columns;
        this.rows = height;
        this.letters = new String[height];
    }

    public int getColumns() { return columns; }

    public int getRows() { return rows; }

    public String[] getLetters() { return letters; }

    public void setColumns(int columns) { this.columns = columns; }

    public void setRows(int rows) { this.rows = rows; }

    public void setLetters(String[] letters) { this.letters = letters;}
}
