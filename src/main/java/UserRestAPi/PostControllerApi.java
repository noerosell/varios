package UserRestAPi;

import Domain.User;
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

    //private static Gson GSON = new Gson();

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

            if (responseUC.roleAdminOk) {
                if (responseUC.userCreated) {
                    this.sendResponse(HttpStatus.CREATED_201, "");
                } else {
                    String response = GSON.toJson("Allready exists.");
                    this.sendResponse(HttpStatus.UNPROCESSABLE_ENTITY_422, response);
                }
            } else {
                String response = GSON.toJson("You don't have admin role");
                this.sendResponse(HttpStatus.UNAUTHORIZED_401, response);
            }
        } else {
            String response = GSON.toJson("Incorrect Http Verb");
            this.sendResponse(HttpStatus.BAD_REQUEST_400, response);
        }
    }

}
