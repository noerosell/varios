package bussinessLogic;

import org.junit.Before;
import org.junit.Test;

import javax.management.relation.Role;

import static org.junit.Assert.*;

/**
 * Created by noe.rosell on 11/12/15.
 */
public class RolesTest {

    private Roles roles;

    @Before
    public void setUp() throws Exception {
        int[] arrRoles={Roles.ROLE_1,Roles.ROLE_2};
        this.roles=new Roles(arrRoles);
    }

    @Test
    public void testAddRole() throws Exception {
        this.roles.addRole(Roles.ROLE_3);
        assertTrue(this.roles.roleExists(Roles.ROLE_3));
    }

    @Test
    public void testDeleteRole() throws Exception {
        this.roles.deleteRole(Roles.ROLE_2);
        assertFalse(this.roles.roleExists(Roles.ROLE_2));
    }

    @Test
    public void testToArray() throws Exception {
        int[] expectedArray={1,2};
        assertArrayEquals(this.roles.toArray(),expectedArray);
    }
}