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

    public synchronized static UserInMemoryRepository getInstance()
    {
        if (instance==null) {
            instance=new UserInMemoryRepository();
            instance.initialFillUp();
        }

        return instance;
    }

    public User getByLogin(String login) {

        for (User row:rows
             ) {
            if (row.getLogin().equals(new String(login)))
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
        return (rows.indexOf(user)>-1);
    }

    private void initialFillUp()
    {


        Role[] rolesArray1={Role.ROLE_1};
        Role[] rolesArray2={Role.ROLE_2};
        Role[] rolesArray3={Role.ROLE_3};
        Role[] rolesArrayAdmin={Role.ROLE_ADMIN};
        User user=new User("user1","password1",rolesArray1);
        rows.add(user);
        user=new User("user2","password2",rolesArray2);
        rows.add(user);
        user=new User("user3","password3",rolesArray3);
        rows.add(user);
        user=new User("admin","admin",rolesArrayAdmin);
        rows.add(user);
    }
}
