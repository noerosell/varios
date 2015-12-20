package UserRestAPi;

import Infrastructure.Presenter.PresenterResponse;
import Infrastructure.Presenter.deletePresenterStrategy;
import Infrastructure.Presenter.postPresenterStrategy;
import org.eclipse.jetty.http.HttpMethod;
import useCases.UserWantsDeleteUser.UserWantsDeleteAUser;
import useCases.UserWantsDeleteUser.UserWantsDeleteAUserRequest;
import useCases.UserWantsDeleteUser.UserWantsDeleteAUserResponse;
import Infrastructure.UserInMemoryRepository;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;

/**
 * Created by noe on 15/12/2015.
 */
public class DeleteControllerApi extends ControllerApiBase {

    private PresenterResponse jsonResponse=new PresenterResponse();

    public void takeAction(HttpExchange httpExchange) throws Exception, IOException {
        if (httpExchange.getRequestMethod().equals(HttpMethod.DELETE.name())) {
            String[] path = httpExchange.getRequestURI().getPath().split("/");
            String username = path[path.length - 1];
            UserInMemoryRepository repository = new UserInMemoryRepository();
            UserWantsDeleteAUserRequest requestUC = new UserWantsDeleteAUserRequest();
            requestUC.username = username;
            requestUC.authUser = this.requestingUser;
            UserWantsDeleteAUser useCase = new UserWantsDeleteAUser(repository);
            UserWantsDeleteAUserResponse responseUC = useCase.execute(requestUC);

            deletePresenterStrategy presenter=new deletePresenterStrategy();
            jsonResponse=presenter.run(responseUC.roleAdminOk,responseUC.userDeleted);
            this.sendResponse(jsonResponse);
        } else

        {
            jsonResponse.httpStatus=HttpStatus.BAD_REQUEST_400;
            jsonResponse.message="Incorrect Http Verb";
            this.sendResponse(jsonResponse);
        }
    }
}
