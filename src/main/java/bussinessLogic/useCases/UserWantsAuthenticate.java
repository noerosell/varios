package bussinessLogic.useCases;

import bussinessLogic.User;
import bussinessLogic.Authenticator;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsAuthenticate {

    public boolean execute(User user, String password)
    {
        Authenticator authenticator=Authenticator.getInstance();
        return authenticator.authenticate(user,password);

    }
}
