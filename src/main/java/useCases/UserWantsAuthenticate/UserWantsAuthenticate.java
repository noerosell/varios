package useCases.UserWantsAuthenticate;

import Domain.PermisionsRepository;
import Domain.User;
import Domain.UserRepository;
import Domain.authenticator.AuthenticationRepository;
import Domain.authenticator.Authenticator;

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

    public UserWantsAuthenticateResponse execute(UserWantsAuthenticateRequest request, Authenticator authenticator)
    {
        UserWantsAuthenticateResponse response=new UserWantsAuthenticateResponse();
        response.isAnAuthUser=false;
        User user= userRepository.getByLogin(request.username);

        if (authenticationRepository.isEmpty()) {
            response.isAnAuthUser= authenticator.authenticate(user, request.password);
        }
        else
        {
            User authUser= authenticationRepository.get(user);
            if (!authenticator.isAuthenticated(user,authUser)) {
                response.isAnAuthUser= authenticator.authenticate(user, request.password);
            }
        }
        return response;
    }
}
