package kantwonskids.donationtrackerg14b.model;

/**
 * Represents an employee at a specific donation center, with the following permissions:
 *     - All permissions of regular users
 *     - The ability to modify the inventory for a specific location
 */
public class LocationEmployee extends User {
    /*
        TODO: Use a Location object instead of a string
     */
    private String location;

    /**
     * Creates a new location employee.
     * @param name the employee's username
     * @param password the employee's password
     */
    public LocationEmployee(String name, String password, String location) {
        super(name, password);
        this.location = location;
    }
}
