package Infrastructure;

import bussinessLogic.PermisionsRepository;
import bussinessLogic.Role;
import bussinessLogic.WebPage;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class PermisionsInMemoryRepository implements PermisionsRepository {
    private ArrayList<Role> permisions=new ArrayList<Role>();

    private static PermisionsInMemoryRepository instance=null;

    private PermisionsInMemoryRepository() {
        //This is a singleton
    };

    public synchronized static PermisionsInMemoryRepository getInstance() throws Exception
    {
        if (instance==null) {
            instance=new PermisionsInMemoryRepository();
            instance.setUpPermissions();
        }
        return instance;
    }

    public boolean add(Role role) {
        return permisions.add(role);
    }

    public Role get(int index)
    {
        return permisions.get(index);
    }

    private void setUpPermissions() {
        permisions.add(WebPage.PAGE_1.getValue(), Role.ROLE_1);
        permisions.add(WebPage.PAGE_2.getValue(), Role.ROLE_2);
        permisions.add(WebPage.PAGE_3.getValue(), Role.ROLE_3);
    }
}
