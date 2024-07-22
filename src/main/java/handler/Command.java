package handler;

public class Command {

    public static final String FLAG_ACTION = "F";
    public static final String CLICK_ACTION = "C";
    public static final String QUIT_ACTION = "Q";
    public static final String INFO_ACTION = "I";
    public static final String EASTER_EGG_ACTION = "SALAMUCCIO";

    String action;

    public Command(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

}
