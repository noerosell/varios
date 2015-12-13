package bussinessLogic;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class Roles {

    public final static int ROLE_1 = 1;
    public final static int ROLE_2 = 2;
    public final static int ROLE_3 = 3;
    public final static int ADMIN = 999;

    public ArrayList<Integer> roles=new ArrayList<Integer>();

    public Roles(int[] startRoles) {
        for (int startRole : startRoles
                ) {
            roles.add(startRole);
        }
    }

    public void addRole(int role) {
        roles.add(role);
    }

    public void deleteRole(int role) {
        roles.remove(new Integer(role));
    }

    public boolean roleExists(int role) {
        return roles.contains(new Integer(role));
    }

    public int[] toArray() {
        int[] formatedArray = new int[roles.size()];
        for (int i=0; i < formatedArray.length; i++)
        {
            formatedArray[i] = roles.get(i).intValue();
        }
        return formatedArray;

    }

}
