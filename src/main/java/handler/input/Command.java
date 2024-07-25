package handler.input;

/**
 * The {@code Command} class represents a command with a specific action.
 */
public class Command {

    /**
     * The {@code Action} enum representing the possible actions of a command.
     */
    public enum Action {
        FLAG_ACTION,
        CLICK_ACTION,
        QUIT_ACTION,
        INFO_ACTION,
        EASTER_EGG_ACTION
    }

    private final Action action;

    /**
     * Constructs a new {@code Command} with the specified action.
     *
     * @param action The action associated with this command.
     */
    public Command(Action action) {
        this.action = action;
    }

    /**
     * Returns the action associated with this command.
     *
     * @return The action associated with this command.
     */
    public Action getAction() {
        return action;
    }

}
