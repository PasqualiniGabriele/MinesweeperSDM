package handler.input;

public class Command {

    public enum Action {FLAG_ACTION, CLICK_ACTION, QUIT_ACTION, INFO_ACTION, EASTER_EGG_ACTION}

    Action action;

    public Command(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

}
