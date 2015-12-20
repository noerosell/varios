package Infrastructure.Presenter;

import org.eclipse.jetty.http.HttpStatus;

/**
 * Created by noe on 19/12/2015.
 */
public class putPresenterStrategy {
    public PresenterResponse run(Object... params)
    {
        PresenterResponse response=new PresenterResponse();
        if ((boolean)params[0]) {
            if (!(boolean)params[1]) {
                response.httpStatus=HttpStatus.NO_CONTENT_204;
                response.message="";
            } else {
                response.httpStatus=HttpStatus.CREATED_201;
                response.message="";
            }
        } else {
            response.httpStatus=HttpStatus.UNAUTHORIZED_401;
            response.message="You don't have admin role";
        }

        return  response;
    }
}
