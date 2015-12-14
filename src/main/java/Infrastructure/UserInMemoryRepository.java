package Infrastructure;

import bussinessLogic.Role;
import bussinessLogic.User;
import bussinessLogic.UserRepository;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 11/12/15.
 * Simple Repostiry implementation, we use User Class from BussnessLogic in order to
 * save time only.
 */
public class UserInMemoryRepository implements UserRepository {


    private ArrayList<User> rows=new ArrayList<User>();

    private static UserInMemoryRepository instance=null;

    private UserInMemoryRepository() {
      //This is a singleton
    };

    public synchronized static UserInMemoryRepository getInstance() throws Exception
    {
        if (instance==null) {
            instance=new UserInMemoryRepository();
            try {
                instance.initialFillUp();
            }catch (Exception e) {
                throw new Exception("Something nasty has ocurred initilizing in memory user repository");
            }
        }

        return instance;
    }

    public User getByLogin(String login) {

        for (User row:rows
             ) {
            if (row.getUsername().equals(new String(login)))
            {
                return row;
            }
        }
        return null;
    }

    public void save(User user)
    {
        rows.add(user);
    }

    public void delete(User user) {
        rows.remove(user);
    }

    public boolean exists(User user) {
        return (rows.contains(user));
    }

    public boolean exists(String username) { return this.getByLogin(username)!=null;}

    private void initialFillUp() throws Exception
    {
        Role[] rolesArray1={Role.ROLE_1};
        Role[] rolesArray2={Role.ROLE_2};
        Role[] rolesArray3={Role.ROLE_3};
        Role[] rolesArrayAdmin={Role.ROLE_ADMIN};
        User user=new User("user1","Password1",rolesArray1);
        rows.add(user);
        user=new User("user2","Password2",rolesArray2);
        rows.add(user);
        user=new User("user3","Password3",rolesArray3);
        rows.add(user);
        user=new User("admin","Admin1",rolesArrayAdmin);
        rows.add(user);
    }
}
