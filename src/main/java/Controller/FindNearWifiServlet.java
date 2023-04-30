package Controller;

/*
    @author Nayoon
 */

import DAO.WifiDAO;
import DTO.WifiDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FindNearWifiServlet", urlPatterns = {"/FindNearWifiServlet"})
public class FindNearWifiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String myLatStr = request.getParameter("myLat");
        String myLntStr = request.getParameter("myLnt");

        if (myLatStr == null || myLntStr == null) {
            response.getWriter().println("Invalid input!");
            return;
        }

        double myLat = Double.parseDouble(myLatStr);
        double myLnt = Double.parseDouble(myLntStr);

        WifiDAO wifiDAO = new WifiDAO();

        try {
            List<WifiDTO> wifiList = wifiDAO.findNearWifiDB(myLat, myLnt);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new Gson().toJson(wifiList));
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        } catch (IOException e) {
            response.getWriter().println("I/O error: " + e.getMessage());
        }
    }
}
