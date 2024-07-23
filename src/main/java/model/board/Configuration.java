
package model.board;

/**
 * This enum representing different configurations for the game board, including the number of bombs,
 * and the width and height of the board for different difficulty levels.
 */
public enum Configuration {
    EASY(10, 8, 8),
    MEDIUM(40, 16, 16),
    HARD(99, 30, 16);

    private final int numOfBombs;
    private final int width;
    private final int height;

    /**
     * Constructs a {@code Configuration} enum with the specified number of bombs,
     * width, and height.
     *
     * @param numOfBombs The number of bombs in the configuration.
     * @param width The width of the game board.
     * @param height The height of the game board.
     */
    Configuration(int numOfBombs, int width, int height) {
        this.numOfBombs = numOfBombs;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the number of bombs for this configuration.
     *
     * @return The number of bombs.
     */
    public int getNumOfBombs() {
        return numOfBombs;
    }

    /**
     * Returns the width of the game board for this configuration.
     *
     * @return The width of the game board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the game board for this configuration.
     *
     * @return The height of the game board.
     */
    public int getHeight() {
        return height;
    }
}