package kantwonskids.donationtrackerg14b.model;

/**
 * @author Ethan Wilson
 *
 *
 * A singleton instance of the model that controllers
 * can access
 */
public class Model {
    // instance of the class
    private static Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    // so that we can skip remaking methods in this class
    // security is retarded
    public UserList _userList;

    /**
     * creates a new model
     */
    private Model() {
        _userList = new UserList();
    }
}
