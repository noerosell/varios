package UserRestAPi;

import Domain.User;
import Infrastructure.Presenter.PresenterResponse;
import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpMethod;
import useCases.UserWantsViewAUser.UserWantsViewAUser;
import useCases.UserWantsViewAUser.UserWantsViewAUserRequest;
import useCases.UserWantsViewAUser.UserWantsViewAUserResponse;
import Infrastructure.UserInMemoryRepository;
import Infrastructure.Presenter.getPresenterStrategy;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;



/**
 * Created by noe on 15/12/2015.
 */
public class GetControllerApi extends ControllerApiBase {

    private PresenterResponse jsonResponse=new PresenterResponse();


    public void takeAction(HttpExchange httpExchange) throws IOException,Exception {

        if (httpExchange.getRequestMethod().equals(HttpMethod.GET.name())) {

            String[] path = httpExchange.getRequestURI().getPath().split("/");
            String username = path[path.length - 1];
            UserInMemoryRepository repository = new UserInMemoryRepository();
            UserWantsViewAUserRequest requestUC = new UserWantsViewAUserRequest(username,this.requestingUser);
            UserWantsViewAUser useCase = new UserWantsViewAUser(repository);
            UserWantsViewAUserResponse responseUC = useCase.execute(requestUC);


            getPresenterStrategy presenter=new getPresenterStrategy();
            jsonResponse=presenter.run(responseUC.roleAdminOk,responseUC.user);
            this.sendResponse(jsonResponse);

        }
        else
        {
            jsonResponse.httpStatus=HttpStatus.BAD_REQUEST_400;
            jsonResponse.message="Incorrect Http Verb";
            this.sendResponse(jsonResponse);
        }

    }
}
