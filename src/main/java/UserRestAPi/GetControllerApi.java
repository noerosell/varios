package UserRestAPi;

import org.eclipse.jetty.http.HttpMethod;
import useCases.UserWantsViewAUser.UserWantsViewAUser;
import useCases.UserWantsViewAUser.UserWantsViewAUserRequest;
import useCases.UserWantsViewAUser.UserWantsViewAUserResponse;
import Infrastructure.UserInMemoryRepository;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;
import java.io.IOException;



/**
 * Created by noe on 15/12/2015.
 */
public class GetControllerApi extends ControllerApiBase {


    public void takeAction(HttpExchange httpExchange) throws IOException,Exception {

        if (httpExchange.getRequestMethod().equals(HttpMethod.GET.name())) {

            String[] path = httpExchange.getRequestURI().getPath().split("/");
            String username = path[path.length - 1];
            UserInMemoryRepository repository = new UserInMemoryRepository();
            UserWantsViewAUserRequest requestUC = new UserWantsViewAUserRequest(username,this.requestingUser);
            UserWantsViewAUser useCase = new UserWantsViewAUser(repository);
            UserWantsViewAUserResponse responseUC = useCase.execute(requestUC);

            if (responseUC.roleAdminOk && responseUC.user!=null)
            {
                String response=GSON.toJson(responseUC.user);
                this.sendResponse(HttpStatus.OK_200,response);
            }
            else if (!responseUC.roleAdminOk) {

                String response = GSON.toJson("You don't have admin role");
                this.sendResponse(HttpStatus.UNAUTHORIZED_401,response);
            }
            else
            {
                this.sendResponse(HttpStatus.NOT_FOUND_404, "");
            }
        }
        else
        {
            String response = GSON.toJson("Incorrect Http Verb");
            this.sendResponse(HttpStatus.BAD_REQUEST_400,response);
        }

    }
}
