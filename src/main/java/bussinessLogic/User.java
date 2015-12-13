package bussinessLogic;

import bussinessLogic.Roles;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class User {

    private String login;
    private String password;
    private Roles roles;
    private int sessionId;


    public User(String startlogin, String startPassword, int[] startRoles)
    {
        login=startlogin;
        password=startPassword;
        roles=new Roles(startRoles);
    }

    public void setPassword(String newPassword)
    {
        password=newPassword;
    }

    public void addRole(int role)
    {
        roles.addRole(role);
    }

    public void removeRole(int role)
    {
        roles.deleteRole(role);
    }

    public boolean hasRole(int role)
    {
        return roles.roleExists(role);
    }

    public String getPassword()
    {
        return password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setSessionId(int newSessionId)
    {
        sessionId=newSessionId;
    }

    public int getSessionId()
    {
        return sessionId;
    }

}
