package UserRestAPi;

import Domain.User;
import Infrastructure.Presenter.PresenterResponse;
import Infrastructure.Presenter.getPresenterStrategy;
import Infrastructure.Presenter.postPresenterStrategy;
import org.eclipse.jetty.http.HttpMethod;
import useCases.UserWantsCreateANewUser.UserWantsCreateANewUser;
import useCases.UserWantsCreateANewUser.UserWantsCreateANewUserRequest;
import useCases.UserWantsCreateANewUser.UserWantsCreateANewUserResponse;
import Infrastructure.UserInMemoryRepository;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;


/**
 * Created by noe on 15/12/2015.
 */

/**
 * Created by noe on 15/12/2015.
 */
public class PostControllerApi extends PutControllerApi {

    private PresenterResponse jsonResponse=new PresenterResponse();

    public void takeAction(HttpExchange httpExchange) throws IOException, Exception {
        if (httpExchange.getRequestMethod().equals(HttpMethod.POST.name())) {
            User inputUser;
            inputUser = this.getUserFromPayload(httpExchange);

            UserInMemoryRepository repository = new UserInMemoryRepository();
            UserWantsCreateANewUserRequest requestUC = new UserWantsCreateANewUserRequest(
                    inputUser,
                    this.requestingUser);
            UserWantsCreateANewUser useCase = new UserWantsCreateANewUser(repository);
            UserWantsCreateANewUserResponse responseUC = useCase.execute(requestUC);

            postPresenterStrategy presenter=new postPresenterStrategy();
            jsonResponse=presenter.run(responseUC.roleAdminOk,responseUC.userCreated);
            this.sendResponse(jsonResponse);

        } else {
            jsonResponse.httpStatus=HttpStatus.BAD_REQUEST_400;
            jsonResponse.message="Incorrect Http Verb";
            this.sendResponse(jsonResponse);
        }
    }

}
