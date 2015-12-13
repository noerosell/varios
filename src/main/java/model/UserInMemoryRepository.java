package model;

import bussinessLogic.User;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 11/12/15.
 * Simple Repostiry implementation, we use User Class from BussnessLogic in order to
 * save time only.
 */
public class UserInMemoryRepository implements UserRepository {


    private ArrayList<User> rows;

    private static UserInMemoryRepository instance;

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
            if (row.getLogin()==login)
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


        int[] rolesArray1={1};
        int[] rolesArray2={2};
        int[] rolesArray3={3};
        int[] rolesArrayAdmin={999};
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
