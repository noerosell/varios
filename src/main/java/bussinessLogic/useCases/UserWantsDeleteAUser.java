package bussinessLogic.useCases;

import bussinessLogic.Roles;
import bussinessLogic.User;
import model.UserRepository;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsDeleteAUser {

    public void execute(User user, UserRepository repository, User newUser)
    {
        if (user.hasRole(Roles.ADMIN)) {
             /*we don't know if future implementations of UserRepository will throw exceptions, trues,falses
            or some else, when deleting a row which not exists. we defense of this in this point.
             */
            if (repository.exists(newUser)==true) {
                repository.delete(newUser);
            }

        }
    }
}
