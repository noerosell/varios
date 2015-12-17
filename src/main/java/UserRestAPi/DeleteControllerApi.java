package UserRestAPi;

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

    public void takeAction(HttpExchange httpExchange) throws Exception, IOException {

        String[] path = httpExchange.getRequestURI().getPath().split("/");
        String username = path[path.length - 1];
        UserInMemoryRepository repository = new UserInMemoryRepository();
        UserWantsDeleteAUserRequest requestUC = new UserWantsDeleteAUserRequest();
        requestUC.username = username;
        requestUC.authUser = this.requestingUser;
        UserWantsDeleteAUser useCase = new UserWantsDeleteAUser(repository);
        UserWantsDeleteAUserResponse responseUC = useCase.execute(requestUC);

        if (responseUC.roleAdminOk) {
            if (responseUC.userDeleted) {
                this.sendResponse(HttpStatus.NO_CONTENT_204, "");
            } else {
                this.sendResponse(HttpStatus.NOT_FOUND_404, "");
            }
        } else {
            String response = GSON.toJson("You don't have admin role");
            this.sendResponse(HttpStatus.UNAUTHORIZED_401, response);
        }

    }
}
