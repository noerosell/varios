package Domain.useCases.UserWantsViewAUser;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class UserWantsViewAUserRequest {

    public String username;
    public String authUser;

    public UserWantsViewAUserRequest(String receivedUsernsame, String existentUser)
    {
        username=receivedUsernsame;
        authUser=existentUser;
    }
}
