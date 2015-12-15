package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class RoleTest {

    private Role role;

    @Test
    public void testGetValue() throws Exception {
        assertEquals(Role.ROLE_1.getValue(),1);

    }

    @Test
    public void testSameValueAs() throws Exception {
        assertTrue(Role.ROLE_1.sameValueAs(Role.ROLE_1));
        assertFalse(Role.ROLE_1.sameValueAs(Role.ROLE_2));
    }
}