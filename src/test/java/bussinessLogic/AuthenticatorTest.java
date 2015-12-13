package bussinessLogic;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class AuthenticatorTest {

    private Authenticator authenticator;
    private User mockedUser;

    @Before
    public void setUp() throws Exception {
        mockedUser=mock(User.class);
        authenticator=Authenticator.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        Authenticator otherInstance=Authenticator.getInstance();
        assertEquals(otherInstance,authenticator);

    }

    @Test
    public void testAuthenticate() throws Exception {
        when(mockedUser.getPassword()).thenReturn("password1");
        assertTrue(authenticator.authenticate(mockedUser,"password1"));

    }

    @Test
    public void testIsAuthenticated() throws Exception {
        when(mockedUser.getSessionId()).thenReturn(1);
        assertTrue(authenticator.isAuthenticated(mockedUser));
        when(mockedUser.getSessionId()).thenReturn(-1);
        assertFalse(authenticator.isAuthenticated(mockedUser));

    }

    @Test
    public void testHasPrivilegesFor() throws Exception {
        when(mockedUser.hasRole(Roles.ROLE_1)).thenReturn(true);
        assertTrue(authenticator.hasPrivilegesFor(mockedUser,WebPage.PAGE_1));
        when(mockedUser.hasRole(Roles.ROLE_1)).thenReturn(false);
        assertFalse(authenticator.hasPrivilegesFor(mockedUser,WebPage.PAGE_1));

    }
}