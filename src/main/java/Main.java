import Infrastructure.UserInMemoryRepository;
import bussinessLogic.User;
import bussinessLogic.useCases.UserWantsAuthenticate;
import bussinessLogic.useCases.UserWantsAuthenticateRequest;
import bussinessLogic.useCases.UserWantsAuthenticateResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Base64;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;

public class Main {


    public static void main(String[] args) {
            get("/api/user", (request, response) -> {
                response.status(HttpStatus.OK_200);
                return "This is the Users Api, you can create: /api/user/create" +
                        "modify: /api/user/modify/{username}" +
                        "or delete: /api/user/delete/{username}";
            });


        before(((request, response) -> {

            String preAuth=request.headers("Authorization");
            preAuth=preAuth.substring(preAuth.indexOf(" ")+1);
            byte[] authHeader=Base64.getMimeDecoder().decode(preAuth);
            String[] dataAuth=new String(authHeader).split(":");
            UserInMemoryRepository repository=UserInMemoryRepository.getInstance();
            UserWantsAuthenticate useCase=new UserWantsAuthenticate(repository);
            UserWantsAuthenticateRequest requestUC=new UserWantsAuthenticateRequest(dataAuth[0],dataAuth[1]);

            UserWantsAuthenticateResponse responseUC=useCase.execute(requestUC);
            if (!responseUC.isAnAuthUser) {
                halt(401, "unauthorized");
            }
        }));
        }

    }