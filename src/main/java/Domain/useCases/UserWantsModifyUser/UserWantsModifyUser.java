package Domain.useCases.UserWantsModifyUser;

import Domain.Role;
import Domain.User;
import Domain.UserRepository;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsModifyUser {

    private UserRepository repository;

    public UserWantsModifyUser(UserRepository receivedRepository)
    {
        repository=receivedRepository;
    }

    public UserWantsModifyUserResponse execute(UserWantsModifyUserRequest request)
    {
        User user=repository.getByLogin(request.authUser);
        UserWantsModifyUserResponse response=new UserWantsModifyUserResponse();
        response.roleAdminOk=false;
        response.userCreated=false;
        if (user.hasRole(Role.ROLE_ADMIN)) {
            response.userModified=false;
            response.roleAdminOk=true;
            if (repository.exists(request.user)==true) {
                response.userCreated=true;
            }
            else
            {
                response.userModified=true;
            }
            repository.save(request.user);
        }
        return response;
    }


}
