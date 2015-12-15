import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    private static int PORT = 8000;

    public static void main(String[] args) throws IOException{
                HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
                httpServer.createContext("/api/user", new HelloApiController());
                httpServer.createContext("/api/user/create", new CreateApiController());
                httpServer.createContext("/api/user/modify", new ModifyApiController());
                httpServer.createContext("/api/user/delete", new DeleteApiController());
                httpServer.createContext("/login", new LoginController());
                httpServer.createContext("/logout", new LogoutController());
                httpServer.createContext("/page", new PageController());
                httpServer.setExecutor(null);
                httpServer.start();
            }
        }
    }
}