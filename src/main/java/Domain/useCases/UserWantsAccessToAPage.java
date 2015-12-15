package Domain.useCases;

import Domain.PermisionsRepository;
import Domain.Role;
import Domain.User;
import Domain.WebPage;
import Domain.authenticator.Authenticator;
import Domain.authenticator.SimpleAuthenticator;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsAccessToAPage {

    private Authenticator authenticator;
    private PermisionsRepository permisionsRepository;

    public UserWantsAccessToAPage(PermisionsRepository receivedPermisionsRepository)
    {
        permisionsRepository=receivedPermisionsRepository;
    }

    public boolean execute(User user, int role, WebPage webPage)
    {
        authenticator=new SimpleAuthenticator();
        Role neededRole = permisionsRepository.get(new Integer(webPage.getValue()));
        if (authenticator.hasPrivilegesFor(user,neededRole))
        {
            return true;
        }
        return false;
    }
}
