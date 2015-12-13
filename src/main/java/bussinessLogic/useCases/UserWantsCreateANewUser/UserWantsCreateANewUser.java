package bussinessLogic.useCases.UserWantsCreateANewUser;

import bussinessLogic.Role;
import bussinessLogic.User;
import bussinessLogic.UserRepository;
import bussinessLogic.useCases.UserWantsAuthenticate.UserWantsAuthenticateResponse;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsCreateANewUser {

    private UserRepository repository;

    public UserWantsCreateANewUser(UserRepository receivedRepository)
    {
        repository=receivedRepository;
    }


    public UserWantsCreateANewUserResponse execute(UserWantsCreateANewUserRequest request)
    {
        User user=repository.getByLogin(request.authUser);
        UserWantsCreateANewUserResponse response=new UserWantsCreateANewUserResponse();
        response.roleAdminOk=false;
        if (user.hasRole(Role.ROLE_ADMIN) || true) {
            /*we don't know if future implementations of UserRepository will throw exceptions, trues,falses
            or some else, when inserting a row which yet exists. we defense of this in this point.
             */
            response.userCreated=false;
            response.roleAdminOk=true;
            if (repository.exists(request.user)==false) {
                repository.save(request.user);
                response.userCreated=true;
            }
        }
        return response;
    }
}
