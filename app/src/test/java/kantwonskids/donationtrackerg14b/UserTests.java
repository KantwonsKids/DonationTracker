package kantwonskids.donationtrackerg14b;

import org.junit.Test;

import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.User;
import kantwonskids.donationtrackerg14b.model.UserRole;

import static org.junit.Assert.*;

/**
 * Tests for the User Class
 */
public class UserTests {
    @Test
    public void userToString_guest() {
        User guest = new User("", "", UserRole.GUEST);
        assertEquals(guest.toString(), UserRole.GUEST.toString());
    }

    @Test
    public void userToString_locationEmployee() {
        Location location = new Location(0, "fake location", 0.0,
                0.0, "fake", "fake", "fake", 0,
                "fake", "fake", "fake", null);
        User locationEmployee = new User("username", "password", UserRole.LOCATION_EMPLOYEE, location);
        String expected = "Username: username" +
                            "\nLocation Employee" +
                            "\nAssigned Location: " + location;
        assertEquals(locationEmployee.toString(), expected);
    }

    @Test
    public void userToString_manager() {
        User manager = new User("username", "password", UserRole.MANAGER);
        String expected = "Username: username\n" +
                            UserRole.MANAGER.toString();
        assertEquals(manager.toString(), expected);

    }

    @Test
    public void userToString_administrator() {
        User administrator = new User("username", "password", UserRole.ADMINISTRATOR);
        String expected = "Username: username\n" +
                            UserRole.ADMINISTRATOR.toString();
        assertEquals(administrator.toString(), expected);

    }
}