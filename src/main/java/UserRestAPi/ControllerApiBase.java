package UserRestAPi;

import Domain.User;
import Domain.authenticator.SimpleAuthenticator;
import useCases.UserWantsAuthenticate.UserWantsAuthenticate;
import useCases.UserWantsAuthenticate.UserWantsAuthenticateRequest;
import useCases.UserWantsAuthenticate.UserWantsAuthenticateResponse;
import Infrastructure.AuthenticationInMemoryRepository;
import Infrastructure.PermisionsInMemoryRepository;
import Infrastructure.UserInMemoryRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


/**
 * Created by noe.rosell on 15/12/15.
 */
abstract public class ControllerApiBase implements HttpHandler {

    private HttpExchange httpExchange;

    protected static String requestingUser;

    protected Gson GSON = new Gson();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        this.httpExchange = httpExchange;
        Headers requestHeaders = httpExchange.getRequestHeaders();

        if (!this.isCorrectContentNegotiation(requestHeaders)) {
            this.sendResponse(HttpStatus.NOT_IMPLEMENTED_501, "Sorry, only json is accepted");
        }

        String[] dataAuth = this.getAuthorizationData(requestHeaders);
        UserInMemoryRepository userRepository;
        AuthenticationInMemoryRepository authRepository;
        PermisionsInMemoryRepository permRepository;

        requestingUser = dataAuth[0];
        try {

            userRepository = new UserInMemoryRepository();
            authRepository = new AuthenticationInMemoryRepository();
            permRepository = new PermisionsInMemoryRepository();;

            UserWantsAuthenticate useCase = new UserWantsAuthenticate(userRepository, permRepository, authRepository);
            UserWantsAuthenticateRequest requestUC = new UserWantsAuthenticateRequest(dataAuth[0], dataAuth[1]);

            UserWantsAuthenticateResponse responseUC = useCase.execute(requestUC, new SimpleAuthenticator());
            if (!responseUC.isAnAuthUser) {
                String response = "Bad username or password";
                httpExchange.sendResponseHeaders(HttpStatus.UNAUTHORIZED_401, response.length());
            }
            requestingUser = dataAuth[0];
            this.takeAction(httpExchange);
        } catch (Exception e) {
            String response = e.getMessage();
            httpExchange.sendResponseHeaders(HttpStatus.SERVICE_UNAVAILABLE_503, response.length());
        }


    }

    protected String[] getAuthorizationData(Headers requestHeaders) {
        String preAuth = requestHeaders.getFirst("Authorization");
        preAuth = preAuth.substring(preAuth.indexOf(" ") + 1);
        byte[] authHeader = Base64.getMimeDecoder().decode(preAuth);
        return new String(authHeader).split(":");
    }

    protected boolean isCorrectContentNegotiation(Headers requestHeaders) throws IOException {
        String[] contentTypes = requestHeaders.getFirst("Content-type").split(";");
        for (String contentType : contentTypes
                ) {
            if (contentType.equals("application/json")) {
                return true;
            }
        }
        return false;
    }

    protected void takeAction(HttpExchange httpExchange) throws Exception {
    }

    protected String getBodyRequest(InputStream is) throws IOException {
        String bodyRequest;
        StringBuilder buf;
        int b;
        is = httpExchange.getRequestBody();
        buf = new StringBuilder();
        while ((b = is.read()) != -1) {
            buf.append((char) b);
        }
        is.close();
        bodyRequest = buf.toString();
        return bodyRequest;
    }

    protected void sendResponse(int httpStatusCode, String responseBody) throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(httpStatusCode, responseBody.getBytes().length);
        httpExchange.getResponseBody().write(responseBody.getBytes());
        httpExchange.close();
    }

    protected User getUserFromPayload(HttpExchange httpExchange) throws IOException {
        User inputUser;
        try {
            String bodyRequest = this.getBodyRequest(httpExchange.getRequestBody());
            inputUser = GSON.fromJson(bodyRequest, User.class);
        } catch (JsonSyntaxException e) {
            inputUser = (User) null;
            String response = GSON.toJson("Invalid format or data received ");
            this.sendResponse(HttpStatus.BAD_REQUEST_400, response);
        }
        return inputUser;
    }
}
