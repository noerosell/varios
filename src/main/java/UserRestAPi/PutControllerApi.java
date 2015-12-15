package UserRestAPi;

import Domain.User;
import Domain.useCases.UserWantsModifyUser.UserWantsModifyUser;
import Domain.useCases.UserWantsModifyUser.UserWantsModifyUserRequest;
import Domain.useCases.UserWantsModifyUser.UserWantsModifyUserResponse;
import Infrastructure.UserInMemoryRepository;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;

/**
 * Created by noe on 15/12/2015.
 */
public class PutControllerApi extends ControllerApiBase {

    //private static Gson GSON = new Gson();

    public void takeAction(HttpExchange httpExchange) throws Exception,IOException {

        User inputUser;
        inputUser = this.getUserFromPayload(httpExchange);

        UserInMemoryRepository repository = UserInMemoryRepository.getInstance();
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

    }
}
