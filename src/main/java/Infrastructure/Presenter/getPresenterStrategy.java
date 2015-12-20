package Infrastructure.Presenter;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
/**
 * Created by noe.rosell on 18/12/15.
 */
public class getPresenterStrategy implements PresenterStrategy{

    protected Gson GSON = new Gson();

    public void getPresenterStrategy() {

    }

    public PresenterResponse run(Object... params)
    {
        PresenterResponse response=new PresenterResponse();
        if (((boolean)params[0]) && params[1]!=null)
        {
            response.message=GSON.toJson(params[1]);
            response.httpStatus = HttpStatus.OK_200;
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
