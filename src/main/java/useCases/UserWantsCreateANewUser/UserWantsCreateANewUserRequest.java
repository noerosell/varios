package useCases.UserWantsCreateANewUser;

import Domain.User;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class UserWantsCreateANewUserRequest {

    public User user;
    public String authUser;

    public UserWantsCreateANewUserRequest(User newUser, String existentUser)
    {
        user=newUser;
        authUser=existentUser;
    }
}
