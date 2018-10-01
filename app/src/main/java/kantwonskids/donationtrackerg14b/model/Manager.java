package kantwonskids.donationtrackerg14b.model;

/**
 * Represents a manager, who has the following permissions:
 *     - All permissions of a location employee
 *     - Ability to modify information at any location
 */
public class Manager extends User {
    /**
     * Creates a new manager.
     * @param username the manager's username
     * @param password the manager's password
     */
    public Manager(String username, String password) {
        super(username, password);
    }
}

