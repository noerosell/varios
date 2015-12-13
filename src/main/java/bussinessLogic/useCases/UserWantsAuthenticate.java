package bussinessLogic.useCases;

import bussinessLogic.User;
import bussinessLogic.Authenticator;
import bussinessLogic.UserRepository;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsAuthenticate {

    private UserRepository repository;

    public UserWantsAuthenticate(UserRepository receivedRepository)
    {
        repository=receivedRepository;
    }

    public boolean execute(String username, String password)
    {
        User user=repository.getByLogin(username);
        Authenticator authenticator=Authenticator.getInstance();
        return authenticator.authenticate(user,password);

    }
}
