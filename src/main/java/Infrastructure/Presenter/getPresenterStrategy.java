package Infrastructure.Presenter;

import org.eclipse.jetty.http.HttpStatus;
/**
 * Created by noe.rosell on 18/12/15.
 */
public class getPresenterStrategy implements PresenterStrategy{

    public void getPresenterStrategy() {

    }

    public PresenterResponse run(Object... params)
    {
        PresenterResponse response=new PresenterResponse();
        if (((boolean)params[0]) && params[1]!=null)
        {
            response.httpStatus = HttpStatus.OK_200;
            response.message="";
        }
        else if (!(boolean)params[0]) {
           response.httpStatus=HttpStatus.UNAUTHORIZED_401;
           response.message="You don't have admin role";
        }
        else
        {
            response.httpStatus = HttpStatus.NOT_FOUND_404;
            response.message="";
        }
        return  response;
    }
}
