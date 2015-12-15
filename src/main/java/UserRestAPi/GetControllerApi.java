package UserRestAPi;

import Domain.User;
import Domain.useCases.UserWantsViewAUser.UserWantsViewAUser;
import Domain.useCases.UserWantsViewAUser.UserWantsViewAUserRequest;
import Domain.useCases.UserWantsViewAUser.UserWantsViewAUserResponse;
import Infrastructure.UserInMemoryRepository;
import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * Created by noe on 15/12/2015.
 */
public class GetControllerApi extends ControllerApiBase {

    public class UserGsonSerializer implements JsonSerializer<User> {
        public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
            int[] roles=user.getRoles().toArray();
            String rolesString="";
            for (int rol :roles
                    ) {
                rolesString=rolesString+Integer.toString(rol)+",";
            }
            rolesString=rolesString.substring(0,rolesString.length()-1);
            String finalString="{\"username\":\""+user.getUsername()+"\",\"roles\":["+rolesString+"]}";
        return new JsonPrimitive(finalString);
        }
    }



    public void takeAction(HttpExchange httpExchange) throws IOException,Exception {
        if (httpExchange.getRequestMethod().equals("GET")) {
            String[] path = httpExchange.getRequestURI().getPath().split("/");
            String username = path[path.length - 1];
            UserInMemoryRepository repository = UserInMemoryRepository.getInstance();
            UserWantsViewAUserRequest requestUC = new UserWantsViewAUserRequest(username,this.requestingUser);
            UserWantsViewAUser useCase = new UserWantsViewAUser(repository);
            UserWantsViewAUserResponse responseUC = useCase.execute(requestUC);

            if (responseUC.roleAdminOk && responseUC.user!=null)
            {
                GsonBuilder gsonBuilder = new GsonBuilder();

                gsonBuilder.registerTypeAdapter(User.class, new UserGsonSerializer());
                Gson gson = gsonBuilder.create();
                String response=gson.toJson(responseUC.user);
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
