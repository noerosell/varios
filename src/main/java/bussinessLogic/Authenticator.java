package bussinessLogic;

import java.util.ArrayList;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class Authenticator {

    private int nextSessionId;

    private ArrayList<Role> permisions=new ArrayList<Role>();

    private static Authenticator instance;


    private Authenticator() {
        //This is a Singleton, not constructor provided.
        this.setUpPermissions();
    }

    //thread safe is a good enhacement, we have concurrence.
    public synchronized static Authenticator getInstance() {
        if (instance==null) {
            instance=new Authenticator();
            instance.setUpPermissions();
        }
        return instance;
    }

    public boolean authenticate(User user,String password) {
        if (user.getPassword() == password) {
            user.setSessionId(this.generateNewSessionid());
            return true;
        }
        return false;
    }

    public int generateNewSessionid() {
        return nextSessionId++;
    }

    public boolean isAuthenticated(User user) {
        if (user.getSessionId() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasPrivilegesFor(User user,WebPage webPage) {
        Role neededRole = permisions.get(new Integer(webPage.getValue()));
        return user.hasRole(neededRole);
    }

    private void setUpPermissions() {
        permisions.add(WebPage.PAGE_1.getValue(), Role.ROLE_1);
        permisions.add(WebPage.PAGE_2.getValue(), Role.ROLE_2);
        permisions.add(WebPage.PAGE_3.getValue(), Role.ROLE_3);
    }

}
