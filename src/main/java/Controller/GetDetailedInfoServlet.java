package Controller;

import DAO.WifiDAO;
import DTO.WifiDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
    @author Nayoon
 */
@WebServlet(name = "GetDetailedInfoServlet", urlPatterns = {"/GetDetailedInfoServlet"})
public class GetDetailedInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // MGR_NO 파라미터 받기
        String mgrNo = request.getParameter("mgrNo");

        // distance 파라미터 받기
        String distance = request.getParameter("distance");

        if (mgrNo == null || distance == null) {
            return;
        }

        WifiDAO wifiDAO = new WifiDAO();

        try {
            WifiDTO wifiDTO = wifiDAO.getDetailedInfo(mgrNo);
            request.setAttribute("wifiDTO", wifiDTO);
            request.setAttribute("distance", distance);
            request.getRequestDispatcher("/wifi-detailed-info.jsp").forward(request, response);
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        } catch (IOException e) {
            response.getWriter().println("I/O error: " + e.getMessage());
        }
    }
}
