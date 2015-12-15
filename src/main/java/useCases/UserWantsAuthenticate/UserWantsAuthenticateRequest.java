package useCases.UserWantsAuthenticate;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class UserWantsAuthenticateRequest {
    public String username;
    public String password;

    public UserWantsAuthenticateRequest(String name, String passw)
    {
        username=name;
        password=passw;
    }

}
