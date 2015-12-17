package Infrastructure;

import Domain.User;
import Domain.authenticator.AuthenticationRepository;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class AuthenticationInMemoryRepository implements AuthenticationRepository {
    private static ArrayList<User> usersAuthenticated=new ArrayList<User>();

    public boolean add(User user)
    {
        return usersAuthenticated.add(user);
    }

    public boolean contains(User user)
    {
        return usersAuthenticated.contains(user);
    }

    public User get(User user)
    {
            return usersAuthenticated.get(usersAuthenticated.indexOf(user));
    }

    public boolean isEmpty() {
        return usersAuthenticated.isEmpty();
    }

}
