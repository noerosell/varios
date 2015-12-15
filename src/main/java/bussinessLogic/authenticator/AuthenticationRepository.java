package bussinessLogic.authenticator;

import bussinessLogic.User;

/**
 * Created by noe.rosell on 14/12/15.
 */
public interface AuthenticationRepository {
    public boolean add(User user);
    public boolean contains(User user);
    public User get(User user);
    public boolean isEmpty();
}
