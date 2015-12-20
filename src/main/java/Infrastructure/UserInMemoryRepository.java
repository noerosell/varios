package Infrastructure;

import Domain.Role;
import Domain.User;
import Domain.UserIdentity;
import Domain.UserRepository;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 11/12/15.
 * Simple Repostiry implementation, we use User Class from BussnessLogic in order to
 * save time only.
 */
public class UserInMemoryRepository implements UserRepository {


    private static ArrayList<User> rows;


    public UserInMemoryRepository() throws Exception {
        try {
            if (rows == null) {
                rows = new ArrayList<User>();
                this.initialFillUp();
            }

        } catch (Exception e) {
            throw new Exception("Something nasty has ocurred initilizing in memory user repository");
        }
    }

    ;


    public User getByLogin(String login) {

        for (User row : rows
                ) {
            if (row.getUsername().equals(new String(login))) {
                return row;
            }
        }
        return null;
    }

    public void save(User user) {
        if (rows.indexOf(user)==-1) {
            rows.add(user);
        }
        else
        {
            rows.set(rows.indexOf(user),user);
        }
    }

    public void delete(User user) {
        rows.remove(user);
    }


    public boolean exists(User user) {
        return (rows.contains(user));
    }

    public boolean exists(String username) {
        return this.getByLogin(username) != null;
    }

    private void initialFillUp() throws Exception {
        Role[] rolesArray1 = {Role.ROLE_1};
        Role[] rolesArray2 = {Role.ROLE_2};
        Role[] rolesArray3 = {Role.ROLE_3};
        Role[] rolesArrayAdmin = {Role.ROLE_ADMIN};
        UserIdentity userIdentity = new UserIdentity("user1");
        User user = new User(userIdentity, "Password1", rolesArray1);
        rows.add(user);
        userIdentity = new UserIdentity("user2");
        user = new User(userIdentity, "Password2", rolesArray2);
        rows.add(user);
        userIdentity = new UserIdentity("user3");
        user = new User(userIdentity, "Password3", rolesArray3);
        rows.add(user);
        userIdentity = new UserIdentity("admin");
        user = new User(userIdentity, "Admin1", rolesArrayAdmin);
        rows.add(user);
    }
}
