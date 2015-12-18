package Domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by noe.rosell on 18/12/15.
 */
public class UserIdentity {

    private String useridentity;

    protected transient String USERNAME_PATTERN = "^[a-z0-9_-]{4,16}$";

    public UserIdentity(String anUseridentity) throws Exception
    {
        if (this.validate(anUseridentity)) {
            this.useridentity = anUseridentity;
        }
        else
            throw new Exception("invalid identity user");

    }

    public String username()
    {
        return this.useridentity;
    }

    private boolean validate(String anUseridentity)
    {
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(anUseridentity);
        return matcher.matches();
    }


}
