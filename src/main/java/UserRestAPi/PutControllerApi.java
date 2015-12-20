package UserRestAPi;

import Domain.User;
import Infrastructure.Presenter.PresenterResponse;
import Infrastructure.Presenter.postPresenterStrategy;
import Infrastructure.Presenter.putPresenterStrategy;
import org.eclipse.jetty.http.HttpMethod;
import useCases.UserWantsModifyUser.UserWantsModifyUser;
import useCases.UserWantsModifyUser.UserWantsModifyUserRequest;
import useCases.UserWantsModifyUser.UserWantsModifyUserResponse;
import Infrastructure.UserInMemoryRepository;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;

/**
 * Created by noe on 15/12/2015.
 */
public class PutControllerApi extends ControllerApiBase {

    private PresenterResponse jsonResponse=new PresenterResponse();

    public void takeAction(HttpExchange httpExchange) throws Exception, IOException {
        if (httpExchange.getRequestMethod().equals(HttpMethod.PUT.name())) {
            User inputUser;
            inputUser = this.getUserFromPayload(httpExchange);

            UserInMemoryRepository repository = new UserInMemoryRepository();
            UserWantsModifyUserRequest requestUC = new UserWantsModifyUserRequest(
                    inputUser,
                    requestingUser);
            UserWantsModifyUser useCase = new UserWantsModifyUser(repository);
            UserWantsModifyUserResponse responseUC = useCase.execute(requestUC);

            putPresenterStrategy presenter=new putPresenterStrategy();
            jsonResponse=presenter.run(responseUC.roleAdminOk,responseUC.userCreated);
            this.sendResponse(jsonResponse);

        } else {
            jsonResponse.httpStatus=HttpStatus.BAD_REQUEST_400;
            jsonResponse.message="Incorrect Http Verb";
            this.sendResponse(jsonResponse);
        }

    }
}
