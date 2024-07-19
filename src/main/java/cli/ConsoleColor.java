package cli;

public enum ConsoleColor {
    RESET("\u001B[0m"),
    GREY("\u001B[37m"),
    DARK_GREY("\u001B[90m"),
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    GOLD("\u001B[33m"),
    RED("\u001B[31m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    YELLOW("\u001B[93m"),
    LIGHT_GREEN("\u001B[92m"),
    WHITE_BG("\u001B[47m\u001B[30m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static String removeAnsiCodes(String text) {
        return text.replaceAll("\\u001B\\[[;\\d]*m", "");
    }
}
