package Domain;

/**
 * Created by noe.rosell on 11/12/15.
 *
 * We want decouple the User repository from its implementation, thus we wiil change the database
 * easily in future.
 */
public interface UserRepository {

    User getByLogin(String id);
    boolean exists(User user);
    boolean exists(String username);
    void save(User user);
    void delete(User user);
}
