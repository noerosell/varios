package RestUserApi;

import static spark.Spark.*;

/**
 * Created by noe.rosell on 13/12/15.
 */
public class UserRestApi {
    public static void UserRestApi(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
