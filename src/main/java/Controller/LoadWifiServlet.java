package Controller;

/*
    @author Nayoon
 */

import API.ApiClient;
import API.ApiConfig;
import API.ApiService;
import DAO.*;
import DTO.WifiResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoadWifiServlet", urlPatterns = {"/LoadWifiServlet"})
public class LoadWifiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApiClient apiClient = new ApiClient(ApiConfig.DOMAIN, ApiConfig.KEY, ApiConfig.TYPE, ApiConfig.SERVICE);

        SqliteConnector connector = new SqliteConnector();
        connector.connect();

        WifiDAO wifiDAO = new WifiDAO(apiClient, connector);

        try {
            wifiDAO.insertWifiToDB();
            WifiResponse wifiResponse = new ApiService(apiClient).getWifiData(1, 1);
            if (wifiResponse != null) {
                int totalCount = wifiResponse.getTotalCount();
                request.setAttribute("totalCount", totalCount);
                request.getRequestDispatcher("load-wifi.jsp").forward(request, response);
            } else {
                System.out.println("WifiResponse is null.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
