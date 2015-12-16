package useCases.UserWantsViewAUser;

import Domain.Role;
import Domain.User;
import Domain.UserRepository;

/**
 * Created by noe on 15/12/2015.
 */
public class UserWantsViewAUser {

    private UserRepository repository;

    public UserWantsViewAUser(UserRepository receivedRepository)
    {
        repository=receivedRepository;
    }

    public UserWantsViewAUserResponse execute(UserWantsViewAUserRequest request)
    {
        User user=repository.getByLogin(request.authUser);
        UserWantsViewAUserResponse response=new UserWantsViewAUserResponse();
        response.roleAdminOk=false;
        if (user.hasRole(Role.ROLE_ADMIN)) {
            response.roleAdminOk=true;
            if (repository.exists(request.username)==true) {
                response.user=repository.getByLogin(request.username);
            }
        }
        return response;
    }


}
