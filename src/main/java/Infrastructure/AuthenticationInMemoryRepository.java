package Infrastructure;

import bussinessLogic.AuthenticationRepository;
import bussinessLogic.User;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class AuthenticationInMemoryRepository implements AuthenticationRepository {
    private ArrayList<User> usersAuthenticated=new ArrayList<User>();

    private static AuthenticationInMemoryRepository instance=null;

    private AuthenticationInMemoryRepository() {
        //This is a singleton
    };

    public synchronized static AuthenticationInMemoryRepository getInstance() throws Exception
    {
        if (instance==null) {
            instance=new AuthenticationInMemoryRepository();
        }
        return instance;
    }

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
