package Infrastructure.Presenter;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;

/**
 * Created by noe.rosell on 18/12/15.
 */
abstract class Presenter {
    private HttpStatus httpStatus;
    private String message;
    protected Gson GSON = new Gson();

    public PresenterResponse render(){
        PresenterResponse response=new PresenterResponse();
        response.httpStatus=this.httpStatus;
        response.message= GSON.toJson(this.message);
        return response;
    }
}
