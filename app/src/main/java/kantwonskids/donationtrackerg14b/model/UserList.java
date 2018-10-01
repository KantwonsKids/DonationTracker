package kantwonskids.donationtrackerg14b.model;
import java.util.ArrayList;

/**
 * A class for the list of valid users
 */
public class UserList {

    private ArrayList<User> backingArray;
    private int size;

    /**
     * Constructor for UserList
     */
    UserList() {
        backingArray = new ArrayList<>();
    }


    /**
     * Method to add a new User to the list
     * @param user the user to be added
     */
    public void addUser(User user) {
        size++;
        backingArray.add(user);
    }

    /**
     * Method to remove a user from the list
     * @param user the user to be removed
     */
    public void removeUser(User user) {
        size--;
        backingArray.remove(user);
    }

    /**
     * Method to check if the user is a valid user
     * @param user user to be checked
     * @return if the user is valid or not
     */
    public boolean isValidUser(User user) {
        for (User u : backingArray) {
            if (u.getUsername().equals(user.getUsername()) &&
                    u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if the username is taken
     * @param username username to be checked
     * @return if the username exists or not
     */
    public boolean usernameTaken(String username) {
        for (User u : backingArray) {
            if (username.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param username username for user to check
     * @param password password for user to check
     * @return if user is valid
     */
    public boolean isValidUser(String username, String password) {
        return isValidUser(new User(username, password));
    }

}
