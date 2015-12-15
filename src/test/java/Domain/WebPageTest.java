package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class WebPageTest {

    private WebPage webPage;

    @Test
    public void testGetValue() throws Exception {
        assertEquals(WebPage.PAGE_1.getValue(),0);

    }

    @Test
    public void testSameValueAs() throws Exception {
        assertTrue(WebPage.PAGE_1.sameValueAs(WebPage.PAGE_1));
        assertFalse(WebPage.PAGE_1.sameValueAs(WebPage.PAGE_2));
    }
}