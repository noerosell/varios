package bussinessLogic;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class UserTest {

    private User user;
    private Roles mockedRoles;

    @Before
    public void setUp() throws Exception {
        int[] rolesArray1={1};
        user=new User("user1","password1",rolesArray1);
    }

    @Test
    public void testPassword() throws Exception {
        user.setPassword("new_password");
        assertEquals(user.getPassword(),"new_password");
    }

    @Test
    public void testAddRole() throws Exception {
        mockedRoles=mock(Roles.class);
        when(mockedRoles.roleExists(Roles.ROLE_2)).thenReturn(true);
        user.addRole(Roles.ROLE_2);
        assertTrue(user.hasRole(Roles.ROLE_2));

    }

    @Test
    public void testRemoveRole() throws Exception {
        mockedRoles=mock(Roles.class);
        when(mockedRoles.roleExists(Roles.ROLE_2)).thenReturn(false);
        user.addRole(Roles.ROLE_2);
        user.removeRole(Roles.ROLE_2);
        assertFalse(user.hasRole(Roles.ROLE_2));
    }
    @Test
    public void testLogin() throws Exception {
        assertEquals(user.getLogin(),"user1");
    }

}