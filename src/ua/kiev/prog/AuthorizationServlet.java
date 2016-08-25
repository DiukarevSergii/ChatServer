package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationServlet extends HttpServlet {
    private static Map<String, String> usersMap = new HashMap<>();

    static {
        usersMap.put("user1", "user1");
        usersMap.put("user2", "user2");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if (usersMap.containsKey(login) && usersMap.get(login).equals(pass)){
            resp.setStatus(200);
        } else {
            resp.setStatus(2508);
        }
    }

}
