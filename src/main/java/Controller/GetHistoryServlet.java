package Controller;
/*
    @author Nayoon
 */
import DAO.HistoryDAO;
import DTO.HistoryDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetHistoryServlet", urlPatterns = {"/GetHistoryServlet"})
public class GetHistoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HistoryDAO historyDAO = new HistoryDAO();

        try {
            List<HistoryDTO> histories = historyDAO.getAllHistory();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new Gson().toJson(histories));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
