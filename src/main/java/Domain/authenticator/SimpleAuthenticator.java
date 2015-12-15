package Domain.authenticator;

import Domain.Role;
import Domain.User;


/**
 * Created by noe.rosell on 11/12/15.
 */
public class SimpleAuthenticator implements Authenticator{


    public SimpleAuthenticator() {
    }

    public boolean authenticate(User user, String password) {
        if (user.getPassword().equals(new String(password))) {
            return true;
        }
        return false;
    }

    public boolean isAuthenticated(User userWantsAuth, User userMaybeAuthed) {
        return (userWantsAuth.getUsername()==userMaybeAuthed.getUsername());
    }

    public boolean hasPrivilegesFor(User user, Role neededRole) {
        return user.hasRole(neededRole);
    }

}
