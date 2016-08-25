package chat.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AuthorizationServlet extends HttpServlet {
    private static Map<String, Boolean> usersOnline = new HashMap<>();

    private ResourceBundle res
            = ResourceBundle.getBundle("chat.server.resources.verifiedUsers");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if (res.containsKey(login) && res.getString(login).equals(pass)){
            usersOnline.put(login, true);
            resp.setStatus(200);
        } else {
            resp.setStatus(2508);
        }
        OutputStream os = resp.getOutputStream();
        Gson gson = new GsonBuilder().create();
        os.write(gson.toJson(usersOnline).getBytes());
    }
}
