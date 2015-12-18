package UserRestAPi;

import Domain.User;
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

    public void takeAction(HttpExchange httpExchange) throws Exception, IOException {
        if (httpExchange.getRequestMethod().equals(HttpMethod.POST.name())) {
            User inputUser;
            inputUser = this.getUserFromPayload(httpExchange);

            UserInMemoryRepository repository = new UserInMemoryRepository();
            UserWantsModifyUserRequest requestUC = new UserWantsModifyUserRequest(
                    inputUser,
                    requestingUser);
            UserWantsModifyUser useCase = new UserWantsModifyUser(repository);
            UserWantsModifyUserResponse responseUC = useCase.execute(requestUC);
            if (responseUC.roleAdminOk) {
                if (responseUC.userModified) {
                    this.sendResponse(HttpStatus.NO_CONTENT_204, "");
                } else {
                    this.sendResponse(HttpStatus.CREATED_201, "");
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
