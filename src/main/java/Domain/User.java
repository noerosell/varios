package Domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class User {

    private UserIdentity username;
    private String password;
    private Roles roles;

    protected transient String PASSWORD_PATTERN = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,16})";


    public User(UserIdentity startlogin, String startPassword, Role[] startRoles) throws Exception
    {
        if (!this.validate(startPassword,startRoles)) {
            throw new Exception("Data not valid");
        }
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
        return username.username();
    }

    public Roles getRoles() {return roles;}

    private boolean validate( String startpasswd, Role[] startRoles)
    {
        Pattern pattern;
        Matcher matcher;
        boolean result;

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(startpasswd);
        result=matcher.matches();
        result=result&&(startRoles.length>0);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        User objj=(User) obj;
        return this.getUsername().equals(objj.getUsername());
    }
}
