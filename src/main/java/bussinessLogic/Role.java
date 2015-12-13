package bussinessLogic;

/**
 * Created by noe.rosell on 13/12/15.
 */
public enum Role {
    ROLE_1(1), ROLE_2(2), ROLE_3(3),ROLE_ADMIN(999);

    private int value;

    private Role(int newRole)
    {
        value=newRole;
    }

    public int getValue() {
        return value;
    }

    public boolean sameValueAs(Role otherRole)
    {
        return this.equals(otherRole);
    }
}
