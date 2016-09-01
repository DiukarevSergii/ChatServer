package chat.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class AuthorizationServlet extends HttpServlet {
    static Set<String> usersOnline = new TreeSet<>();

    private ResourceBundle res
            = ResourceBundle.getBundle("verifiedUsers_en");

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if (res.containsKey(login) && res.getString(login).equals(pass)) {
            usersOnline.add(login);
            resp.setStatus(200);
        } else {
            resp.setStatus(2508);
        }
    }

    @Override
    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream os = resp.getOutputStream();
        Gson gson = new GsonBuilder().create();
        os.write(gson.toJson(usersOnline).getBytes());
    }

    @Override
    protected synchronized void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("login");
        usersOnline.remove(user);
    }


}
