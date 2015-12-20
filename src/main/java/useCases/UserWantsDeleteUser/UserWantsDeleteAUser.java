package useCases.UserWantsDeleteUser;

import Domain.Role;
import Domain.User;
import Domain.UserRepository;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsDeleteAUser {

    private UserRepository repository;

    public UserWantsDeleteAUser(UserRepository receivedRepository) {
        repository = receivedRepository;
    }

    public UserWantsDeleteAUserResponse execute(UserWantsDeleteAUserRequest request) {
        User adminUser = repository.getByLogin(request.authUser);
        UserWantsDeleteAUserResponse response = new UserWantsDeleteAUserResponse();
        User toDeleteUser = repository.getByLogin(request.username);
        response.roleAdminOk = false;
        if (adminUser.hasRole(Role.ROLE_ADMIN)) {
            response.userDeleted = false;
            response.roleAdminOk = true;
            if (toDeleteUser != null) {
                if (repository.exists(toDeleteUser.getUsername())) {
                    repository.delete(toDeleteUser);
                    response.userDeleted = true;
                }
            }
        }
        return response;
    }
}
