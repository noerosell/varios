package bussinessLogic.useCases.UserWantsAuthenticate;

import Infrastructure.AuthenticationInMemoryRepository;
import bussinessLogic.*;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsAuthenticate {

    private UserRepository userRepository;
    private AuthenticationRepository authenticationRepository;
    private PermisionsRepository permisionsRepository;

    public UserWantsAuthenticate(UserRepository receivedUserRepository, PermisionsRepository receivedPermisionsRepository, AuthenticationRepository receivedAuthenticationRepository)
    {
        userRepository =receivedUserRepository;
        authenticationRepository=receivedAuthenticationRepository;
        permisionsRepository=receivedPermisionsRepository;
    }

    public UserWantsAuthenticateResponse execute(UserWantsAuthenticateRequest request)
    {
        UserWantsAuthenticateResponse response=new UserWantsAuthenticateResponse();
        response.isAnAuthUser=false;
        User user= userRepository.getByLogin(request.username);
        if (authenticationRepository.isEmpty()) {
            Authenticator authenticator=new Authenticator();
            response.isAnAuthUser= authenticator.authenticate(user, request.password);
        }
        else
        {
            User authUser= authenticationRepository.get(user);
            Authenticator authenticator=new Authenticator();
            if (!authenticator.isAuthenticated(user,authUser)) {
                response.isAnAuthUser= authenticator.authenticate(user, request.password);
            }
        }
        return response;
    }
}
