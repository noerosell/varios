package bussinessLogic;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class Roles {

    public ArrayList<Role> roles=new ArrayList<Role>();

    public Roles(Role[] startRoles) {
        for (Role startRole : startRoles
                ) {
            roles.add(startRole);
        }
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void deleteRole(Role role) {
        roles.remove(role);
    }

    public boolean roleExists(Role role) {
        return roles.contains(role);
    }

    public int[] toArray() {
        int[] formatedArray = new int[roles.size()];
        for (int i=0; i < formatedArray.length; i++)
        {
            formatedArray[i] = roles.get(i).getValue();
        }
        return formatedArray;

    }

}
