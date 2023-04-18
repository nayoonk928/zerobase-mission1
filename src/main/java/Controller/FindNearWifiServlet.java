package Controller;

/*
    @author Nayoon
 */

import DAO.WifiDAO;
import DTO.WifiDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "FindNearWifiServlet", urlPatterns = {"/FindNearWifiServlet"})
public class FindNearWifiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

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
            Map<WifiDTO, Double> wifiList = wifiDAO.findNearWifiDB(myLat, myLnt);
            request.setAttribute("WifiList", wifiList);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        } catch (IOException e) {
            response.getWriter().println("I/O error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
