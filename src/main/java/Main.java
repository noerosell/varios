import Infrastructure.UserInMemoryRepository;
import bussinessLogic.User;
import bussinessLogic.useCases.UserWantsAuthenticate.UserWantsAuthenticate;
import bussinessLogic.useCases.UserWantsAuthenticate.UserWantsAuthenticateRequest;
import bussinessLogic.useCases.UserWantsAuthenticate.UserWantsAuthenticateResponse;
import bussinessLogic.useCases.UserWantsCreateANewUser.UserWantsCreateANewUser;
import bussinessLogic.useCases.UserWantsCreateANewUser.UserWantsCreateANewUserRequest;
import bussinessLogic.useCases.UserWantsCreateANewUser.UserWantsCreateANewUserResponse;
import bussinessLogic.useCases.UserWantsDeleteUser.UserWantsDeleteAUser;
import bussinessLogic.useCases.UserWantsDeleteUser.UserWantsDeleteAUserRequest;
import bussinessLogic.useCases.UserWantsDeleteUser.UserWantsDeleteAUserResponse;
import bussinessLogic.useCases.UserWantsModifyUser.UserWantsModifyUser;
import bussinessLogic.useCases.UserWantsModifyUser.UserWantsModifyUserRequest;
import bussinessLogic.useCases.UserWantsModifyUser.UserWantsModifyUserResponse;
import com.google.gson.JsonSyntaxException;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Base64;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Request;

import static spark.Spark.*;

public class Main {

    private static Gson GSON = new Gson();
    private static String requestingUser;


    public static void main(String[] args) {
            get("/api/user", (request, response) -> {
                response.status(HttpStatus.OK_200);
                return GSON.toJson("Message: wellcome to Api User, you can /api/user/create, /api/user/modify/{username}, /api/user/delete/{username}");
            });

            post("/api/user/create/", (request,response) -> {
                User inputUser;

                try {
                    inputUser = GSON.fromJson(request.body(), User.class);
                }catch (JsonSyntaxException e) {
                    response.status(HttpStatus.BAD_REQUEST_400);
                    return "Invalid format or data received ";
                }
                UserInMemoryRepository repository=UserInMemoryRepository.getInstance();
                UserWantsCreateANewUserResponse responseUC=new UserWantsCreateANewUserResponse();
                UserWantsCreateANewUserRequest requestUC=new UserWantsCreateANewUserRequest(
                        inputUser,
                        requestingUser);
                UserWantsCreateANewUser useCase=new UserWantsCreateANewUser(repository);
                responseUC=useCase.execute(requestUC);

                if (responseUC.roleAdminOk) {
                    if (responseUC.userCreated) {
                        response.status(HttpStatus.CREATED_201);
                    } else {
                        response.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
                        return "Allready exists.";
                    }
                }
                else
                {
                    response.status(HttpStatus.UNAUTHORIZED_401);
                    return "You don't have admin role";
                }
                return true;
            });


            put("/api/user/modify/",(request,response) -> {
                User inputUser;
                try {
                    inputUser = GSON.fromJson(request.body(), User.class);
                }catch (JsonSyntaxException e) {
                    response.status(HttpStatus.BAD_REQUEST_400);
                    return "INVALID JSON";
                }
                UserInMemoryRepository repository=UserInMemoryRepository.getInstance();
                UserWantsModifyUserResponse responseUC=new UserWantsModifyUserResponse();
                UserWantsModifyUserRequest requestUC=new UserWantsModifyUserRequest(
                        inputUser,
                        requestingUser);
                UserWantsModifyUser useCase=new UserWantsModifyUser(repository);
                responseUC=useCase.execute(requestUC);
                if (responseUC.roleAdminOk) {
                    if (responseUC.userModified) {
                        response.status(HttpStatus.NO_CONTENT_204);
                    } else {
                        response.status(HttpStatus.NOT_FOUND_404);
                        return "Not exists.";
                    }
                }
                else
                {
                    response.status(HttpStatus.UNAUTHORIZED_401);
                    return "You don't have admin role";
                }
                return true;
            });

        delete("/api/user/delete/:username",(request,response)-> {
            String username= request.params("username");
            UserInMemoryRepository repository=UserInMemoryRepository.getInstance();
            UserWantsDeleteAUserResponse responseUC=new UserWantsDeleteAUserResponse();
            UserWantsDeleteAUserRequest requestUC=new UserWantsDeleteAUserRequest();
            requestUC.username=username;
            requestUC.authUser=requestingUser;
            UserWantsDeleteAUser useCase=new UserWantsDeleteAUser(repository);
            responseUC = useCase.execute(requestUC);
            if (responseUC.roleAdminOk) {
                if (responseUC.userDeleted) {
                    response.status(HttpStatus.NO_CONTENT_204);
                } else {
                    response.status(HttpStatus.NOT_FOUND_404);
                    return "Not exists.";
                }
            }
            else
            {
                response.status(HttpStatus.UNAUTHORIZED_401);
                return "You don't have admin role";
            }
            return true;
        });

        before(((request, response) -> {

            String preAuth=request.headers("Authorization");
            preAuth=preAuth.substring(preAuth.indexOf(" ")+1);
            byte[] authHeader=Base64.getMimeDecoder().decode(preAuth);
            String[] dataAuth=new String(authHeader).split(":");
            requestingUser=dataAuth[0];
            UserInMemoryRepository repository=UserInMemoryRepository.getInstance();
            UserWantsAuthenticate useCase=new UserWantsAuthenticate(repository);
            UserWantsAuthenticateRequest requestUC=new UserWantsAuthenticateRequest(dataAuth[0],dataAuth[1]);

            UserWantsAuthenticateResponse responseUC=useCase.execute(requestUC);
            if (!responseUC.isAnAuthUser) {
                halt(401, "unauthorized");
            }
            requestingUser=dataAuth[0];
        }));
        }

    }