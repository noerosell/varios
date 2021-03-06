package Domain.authenticator;

import Domain.Role;
import Domain.User;

/**
 * Created by noe.rosell on 15/12/15.
 */
public interface Authenticator {
    public boolean authenticate(User user, String password);

    public boolean isAuthenticated(User userWantsAuth, User userMaybeAuthed);

    public boolean hasPrivilegesFor(User user, Role neededRole);

}
