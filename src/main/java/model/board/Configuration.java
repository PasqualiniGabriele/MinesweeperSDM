
package model.board;

public enum Configuration {
    EASY(10, 8, 8),
    MEDIUM(40, 16, 16),
    HARD(99, 30, 16);

    private final int numOfBombs;
    private final int width;
    private final int height;

    Configuration(int numOfBombs, int width, int height) {
        this.numOfBombs = numOfBombs;
        this.width = width;
        this.height = height;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}