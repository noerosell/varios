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
        Role[] rolesArray1={Role.ROLE_1};
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
        when(mockedRoles.roleExists(Role.ROLE_2)).thenReturn(true);
        user.addRole(Role.ROLE_2);
        assertTrue(user.hasRole(Role.ROLE_2));

    }

    @Test
    public void testRemoveRole() throws Exception {
        mockedRoles=mock(Roles.class);
        when(mockedRoles.roleExists(Role.ROLE_2)).thenReturn(false);
        user.addRole(Role.ROLE_2);
        user.removeRole(Role.ROLE_2);
        assertFalse(user.hasRole(Role.ROLE_2));
    }
    @Test
    public void testLogin() throws Exception {
        assertEquals(user.getLogin(),"user1");
    }

}