package useCases.UserWantsModifyUser;

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
        if (user.hasRole(Role.ROLE_ADMIN)) {
            response.roleAdminOk=true;
            if (repository.exists(request.user)==true) {
                response.userModified=true;
                response.userCreated=false;
            }
            else
            {
                response.userCreated=true;
                response.userModified=false;
            }
            repository.save(request.user);
        }
        return response;
    }


}
