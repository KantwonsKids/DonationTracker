package kantwonskids.donationtrackerg14b.model;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for the list of valid users
 */
public class UserList implements Serializable {

    // Mapping of user names to user objects, for quick lookup of users by name.
    // Make work with searchable lists
    private final Map<String, User> usernameObjectMap;

    /**
     * Creates an empty user list.
     */
    UserList() {
        usernameObjectMap = new HashMap<>();
    }

    /**
     * Method to add a new User to the list
     * @param user the user to be added
     */
    public void addUser(User user) {
        usernameObjectMap.put(user.getUsername(), user);
    }

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     * Method to remove a user from the list
//     * @param user the user to be removed
//     */
//    public void removeUser(User user) {
//        usernameObjectMap.remove(user);
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

    /**
     * Method to check if the user is a valid user
     * @param user user to be checked
     * @return if the user is valid or not
     */
    public boolean isValidUser(User user) {
        return this.usernameObjectMap.containsValue(user);
    }

    /**
     * Method to check if the username is taken
     * @param username username to be checked
     * @return if the username exists or not
     */
    public boolean isUsernameTaken(String username) {
        return this.usernameObjectMap.containsKey(username);
    }

// --Commented out by Inspection START (11/15/18, 12:37 PM):
//    /**
//     *
//     * @param username username for user to check
//     * @param password password for user to check
//     * @return if user is valid
//     */
//    public boolean isValidUser(String username, String password) {
//        User user = usernameObjectMap.get(username);
//        String userPassword = user.getPassword();
//        return this.usernameObjectMap.containsKey(username)
//                && userPassword.equals(password);
//    }
// --Commented out by Inspection STOP (11/15/18, 12:37 PM)

    /**
     * Gets a user by username.
     * @param username the username to search for.
     * @return the User with that username, or null if it does not exist.
     */
    public User getUser(String username) {
        return usernameObjectMap.get(username);
    }

}
