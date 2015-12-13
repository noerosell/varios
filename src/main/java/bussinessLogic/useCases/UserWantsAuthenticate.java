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

    public UserWantsAuthenticateResponse execute(UserWantsAuthenticateRequest request)
    {
        UserWantsAuthenticateResponse response=new UserWantsAuthenticateResponse();
        response.isAnAuthUser=false;
        User user=repository.getByLogin(request.username);
        Authenticator authenticator=Authenticator.getInstance();
        if (!authenticator.isAuthenticated(user)) {
            response.isAnAuthUser= authenticator.authenticate(user, request.password);
        }
        return response;
    }
}
