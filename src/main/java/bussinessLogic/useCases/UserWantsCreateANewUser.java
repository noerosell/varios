package bussinessLogic.useCases;

import bussinessLogic.Roles;
import bussinessLogic.User;
import model.UserRepository;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserWantsCreateANewUser {

    /**
     *
     * @param user
     * @param repository
     * @param newUser
     */
    public void execute(User user,UserRepository repository, User newUser)
    {
        if (user.hasRole(Roles.ADMIN)) {
            /*we don't know if future implementations of UserRepository will throw exceptions, trues,falses
            or some else, when inserting a row which yet exists. we defense of this in this point.
             */
            if (repository.exists(newUser)==false) {
                repository.save(newUser);
            }
        }
    }
}
