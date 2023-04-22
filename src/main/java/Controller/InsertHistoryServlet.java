package Controller;

/*
    @author Nayoon
 */

import DAO.HistoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "InsertHistoryServlet", urlPatterns = {"/InsertHistoryServlet"})
public class InsertHistoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 클라이언트 측에서 전달한 값 받기
        String latStr = request.getParameter("myLat");
        String lntStr = request.getParameter("myLnt");

        if (latStr == null || lntStr == null) {
            response.getWriter().println("Invalid input!");
            return;
        }

        Double myLat = Double.parseDouble(latStr);
        Double myLnt = Double.parseDouble(lntStr);

        // 버튼을 누른 시간 구하기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());

        // HistoryDAO 객체를 생성하고 insertHistory() 메서드를 호출하여 값을 전달
        HistoryDAO historyDAO = new HistoryDAO();
        try {
            historyDAO.insertHistory(myLat, myLnt, formattedDate);
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
