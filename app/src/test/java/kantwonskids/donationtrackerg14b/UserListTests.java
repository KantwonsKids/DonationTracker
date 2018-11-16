package kantwonskids.donationtrackerg14b;


import org.junit.Test;

import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.User;
import kantwonskids.donationtrackerg14b.model.UserList;
import kantwonskids.donationtrackerg14b.model.UserRole;

import static org.junit.Assert.*;

/**
 * @author Aidan Mulaokar
 * @version 1.0
 *
 *  J-Unit tests for the userList to make sure all methods work appropriately. All tests implicitly
 *  test the accuracy of the isValidUser() methods. All User methods called upon have been tested
 *  for accuracy in the UserTests.
 */
public class UserListTests {

    User aidan = new User("Aidan", "password", UserRole.GUEST);
    User ethan = new User("Ethan", "sadBoy1998", UserRole.USER);
    User amanda = new User("Amanda", "moraleOfficer", UserRole.ADMINISTRATOR);
    User juliana = new User("Juliana", "cs1332", UserRole.USER);
    User daniel = new User("Daniel", "overlord", UserRole.MANAGER);

    User[] users = {aidan, ethan, amanda, juliana, daniel};

    UserList userList = new UserList();
    @Test
    public void addUserToList() {

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) {
                userList.addUser(users[i]);
            }
        }
        assertEquals(true, userList.isValidUser("Aidan", "password"));
        assertEquals(false, userList.isValidUser("Jeffrey", "pass"));
        assertEquals(true, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(false, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("John", "sadBoy1998"));
        assertEquals(true, userList.isValidUser("Daniel", "overlord"));

    }

    @Test
    public void getUserFromList() {

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) {
                userList.addUser(users[i]);
            }
        }
        assertEquals(true, userList.isValidUser("Aidan", "password"));
        assertEquals(false, userList.isValidUser("Jeffrey", "pass"));
        assertEquals(true, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(false, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("John", "sadBoy1998"));
        assertEquals(true, userList.isValidUser("Daniel", "overlord"));

        assertEquals(aidan, userList.getUser("Aidan"));
        assertEquals(null, userList.getUser("aidan"));
    }

    @Test
    public void isUsernameInList() {

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) {
                userList.addUser(users[i]);
            }
        }
        assertEquals(true, userList.isValidUser("Aidan", "password"));
        assertEquals(false, userList.isValidUser("Jeffrey", "pass"));
        assertEquals(true, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(false, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("John", "sadBoy1998"));
        assertEquals(true, userList.isValidUser("Daniel", "overlord"));

        assertEquals(true, userList.isUsernameTaken("Aidan"));
        assertEquals(false, userList.isUsernameTaken("aidan"));
    }

    @Test
    public void removeUserFromList() {

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) {
                userList.addUser(users[i]);
            }
        }
        assertEquals(true, userList.isValidUser("Aidan", "password"));
        assertEquals(true, userList.isValidUser(aidan));
        assertEquals(false, userList.isValidUser("Jeffrey", "pass"));
        assertEquals(true, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(false, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("John", "sadBoy1998"));
        assertEquals(true, userList.isValidUser("Daniel", "overlord"));

        for (User user : users) {
            if (userList.isValidUser(user)) {
                userList.removeUser(user.getUsername());
            }
        }

        assertEquals(false, userList.isValidUser(aidan));
        assertEquals(false, userList.isValidUser("Daniel", "overlord"));
        assertEquals(false, userList.isValidUser(amanda));

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) {
                userList.addUser(users[i]);
            }
        }

        assertEquals(true, userList.isValidUser("Aidan", "password"));
        assertEquals(true, userList.isValidUser(aidan));
        assertEquals(false, userList.isValidUser("Jeffrey", "pass"));
        assertEquals(true, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(false, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("John", "sadBoy1998"));
        assertEquals(true, userList.isValidUser("Daniel", "overlord"));

        userList.removeUser(aidan.getUsername());
        assertEquals(false, userList.isValidUser(aidan));
        assertEquals(true, userList.isValidUser("Daniel", "overlord"));
        assertEquals(true, userList.isValidUser(amanda));

    }

    @Test
    public void fullUserListTest() {

        for (User user : users) {
            userList.addUser(user);
        }

        for (User user : users) {
            assertEquals(true, userList.isValidUser(user.getUsername(), user.getPassword()));
        }

        for (User user : users) {
            assertEquals(true, userList.isValidUser(user));
        }

        String username = "";
        for(User user : users) {
            username = user.getUsername();
        }

        assertEquals("Daniel", username);

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 0) {
                userList.removeUser(users[i].getUsername());
            }
        }

        assertEquals(false, userList.isValidUser("Aidan", "password"));
        assertEquals(true, userList.isValidUser("Ethan", "sadBoy1998"));
        assertEquals(false, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(true, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("Daniel", "overlord"));

        assertEquals(false, userList.isUsernameTaken("Aidan"));
        assertEquals(true, userList.isUsernameTaken("Ethan"));
        assertEquals(false, userList.isUsernameTaken("Amanda"));
        assertEquals(true, userList.isUsernameTaken("Juliana"));
        assertEquals(false, userList.isUsernameTaken("Daniel"));

        for (int i = 0; i < users.length; i++) {
            if (i % 2 == 1) {
                userList.removeUser(users[i].getUsername());
            }
        }

        assertEquals(false, userList.isValidUser("Aidan", "password"));
        assertEquals(false, userList.isValidUser("Ethan", "sadBoy1998"));
        assertEquals(false, userList.isValidUser("Amanda", "moraleOfficer"));
        assertEquals(false, userList.isValidUser("Juliana", "cs1332"));
        assertEquals(false, userList.isValidUser("Daniel", "overlord"));

        assertEquals(false, userList.isUsernameTaken("Aidan"));
        assertEquals(false, userList.isUsernameTaken("Ethan"));
        assertEquals(false, userList.isUsernameTaken("Amanda"));
        assertEquals(false, userList.isUsernameTaken("Juliana"));
        assertEquals(false, userList.isUsernameTaken("Daniel"));
    }


}
