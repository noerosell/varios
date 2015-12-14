package bussinessLogic.useCases.UserWantsDeleteUser;

import bussinessLogic.Role;
import bussinessLogic.User;
import bussinessLogic.UserRepository;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsDeleteAUser {

    private UserRepository repository;

    public UserWantsDeleteAUser(UserRepository receivedRepository)
    {
        repository=receivedRepository;
    }

    public UserWantsDeleteAUserResponse execute(UserWantsDeleteAUserRequest request)
    {
        User adminUser=repository.getByLogin(request.authUser);
        User toDeleteUser=repository.getByLogin(request.username);
        UserWantsDeleteAUserResponse response=new UserWantsDeleteAUserResponse();
        response.roleAdminOk=false;
        if (adminUser.hasRole(Role.ROLE_ADMIN) || true) {
            /*we don't know if future implementations of UserRepository will throw exceptions, trues,falses
            or some else, when inserting a row which yet exists. we defense of this in this point.
             */
            response.userDeleted=false;
            response.roleAdminOk=true;
            if (repository.exists(toDeleteUser)==false) {
                repository.delete(toDeleteUser);
                response.userDeleted=true;
            }
        }
        return response;
    }
}
