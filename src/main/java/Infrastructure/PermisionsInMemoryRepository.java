package Infrastructure;

import Domain.PermisionsRepository;
import Domain.Role;
import Domain.WebPage;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 14/12/15.
 */
public class PermisionsInMemoryRepository implements PermisionsRepository {
    private static ArrayList<Role> permisions;

    private static PermisionsInMemoryRepository instance = null;

    public PermisionsInMemoryRepository() {
        if (permisions == null) {
            permisions = new ArrayList<Role>();
            this.setUpPermissions();
        }
    }

    ;


    public boolean add(Role role) {
        return permisions.add(role);
    }

    public Role get(int index) {
        return permisions.get(index);
    }

    private void setUpPermissions() {
        permisions.add(WebPage.PAGE_1.getValue(), Role.ROLE_1);
        permisions.add(WebPage.PAGE_2.getValue(), Role.ROLE_2);
        permisions.add(WebPage.PAGE_3.getValue(), Role.ROLE_3);
    }
}
