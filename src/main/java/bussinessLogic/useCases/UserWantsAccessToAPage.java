package bussinessLogic.useCases;

import bussinessLogic.Authenticator;
import bussinessLogic.User;
import bussinessLogic.WebPage;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsAccessToAPage {

    private Authenticator authenticator;

    public boolean execute(User user, int role, WebPage webPage)
    {
        authenticator=Authenticator.getInstance();

        if (authenticator.isAuthenticated(user) && authenticator.hasPrivilegesFor(user,webPage))
        {
            return true;
        }
        return false;
    }
}
