package useCases.UserWantsModifyUser;

import Domain.User;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class UserWantsModifyUserRequest {

    public User user;
    public String authUser;

    public UserWantsModifyUserRequest(User newUser, String existentUser)
    {
        user=newUser;
        authUser=existentUser;
    }
}
