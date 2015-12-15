package bussinessLogic;

import bussinessLogic.authenticator.Authenticator;
import bussinessLogic.authenticator.SimpleAuthenticator;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class AuthenticatorTest {

    private Authenticator authenticator;
    private User userWantsAuth;
    private User userAuthed;

    @Before
    public void setUp() throws Exception {
        userAuthed=mock(User.class);
        userWantsAuth=mock(User.class);
        authenticator=new SimpleAuthenticator();
    }

    @Test
    public void testAuthenticate() throws Exception {
        when(userWantsAuth.getPassword()).thenReturn("Password1");
        assertTrue(authenticator.authenticate(userWantsAuth,"Password1"));

    }

    @Test
    public void testIsAuthenticated() throws Exception {
        when(userWantsAuth.getUsername()).thenReturn("user1");
        when(userAuthed.getUsername()).thenReturn("user1");
        assertTrue(authenticator.isAuthenticated(userWantsAuth,userAuthed));
    }

    @Test
    public void testIsNotAuthenticated() throws Exception {

        when(userWantsAuth.getPassword()).thenReturn("Password1");
        when(userAuthed.getPassword()).thenReturn("Password2");
        authenticator.authenticate(userWantsAuth,"Password1");
        assertTrue(authenticator.isAuthenticated(userWantsAuth,userAuthed));
    }



    @Test
    public void testHasPrivilegesFor() throws Exception {
        when(userAuthed.hasRole(Role.ROLE_1)).thenReturn(true);
        assertTrue(authenticator.hasPrivilegesFor(userAuthed,Role.ROLE_1));
        when(userAuthed.hasRole(Role.ROLE_1)).thenReturn(false);
        assertFalse(authenticator.hasPrivilegesFor(userAuthed,Role.ROLE_1));

    }
}