package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a generic command.
 */
public interface Command {

    /**
     * Executes the command.
     * @return true if the command succeeded, false if it failed.
     */
    boolean execute();
}
