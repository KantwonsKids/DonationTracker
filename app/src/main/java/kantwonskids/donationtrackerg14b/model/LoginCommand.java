package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a login command. Executed when a user wants to login.
 */
public class LoginCommand implements Command {
    private boolean doLogin() {
        return false;
    }

    @Override
    public boolean execute() {
        return doLogin();
    }

}
