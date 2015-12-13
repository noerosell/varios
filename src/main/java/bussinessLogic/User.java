package bussinessLogic;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class User {

    private String username;
    private String password;
    private Roles roles;
    private int sessionId;


    public User(String startlogin, String startPassword, Role[] startRoles)
    {
        username =startlogin;
        password=startPassword;
        roles=new Roles(startRoles);
    }

    public void setPassword(String newPassword)
    {
        password=newPassword;
    }

    public void addRole(Role role)
    {
        roles.addRole(role);
    }

    public void removeRole(Role role)
    {
        roles.deleteRole(role);
    }

    public boolean hasRole(Role role)
    {
        return roles.roleExists(role);
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setSessionId(int newSessionId)
    {
        sessionId=newSessionId;
    }

    public int getSessionId()
    {
        return sessionId;
    }

    public boolean equals(Object obj) {
        return this.username.equals(((User)obj).username);
    }
}
