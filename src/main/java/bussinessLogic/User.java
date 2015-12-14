package bussinessLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class User {

    private String username;
    private String password;
    private Roles roles;

    protected String USERNAME_PATTERN = "^[a-z0-9_-]{4,16}$";
    protected String PASSWORD_PATTERN = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,16})";


    public User(String startlogin, String startPassword, Role[] startRoles) throws Exception
    {
        if (!this.validate(startlogin,startPassword,startRoles)) {
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
        return username;
    }

    public boolean equals(Object obj) {
        return this.username.equals(((User)obj).username);
    }

    private boolean validate(String startlogin, String startpasswd, Role[] startRoles)
    {
        Pattern pattern;
        Matcher matcher;
        boolean result;
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(startlogin);
        result= matcher.matches();
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(startpasswd);
        result=result&&matcher.matches();
        result=result&&(startRoles.length>0);
        return result;
    }
}
