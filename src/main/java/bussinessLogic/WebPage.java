package bussinessLogic;

/**
 * Created by noe.rosell on 11/12/15.
 * Value Object Web Page
 */
public enum WebPage {
    PAGE_1(0), PAGE_2(1), PAGE_3(2);


    private int value;

    private WebPage(int newPage)
    {
        value=newPage;
    }

    public int getValue() {
        return value;
    }

    public boolean sameValueAs(WebPage otherWebPage)
    {
        return this.equals(otherWebPage);
    }



}
