package Controller;
/*
    @author Nayoon
 */
import DAO.HistoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteHistoryServlet", urlPatterns = {"/DeleteHistoryServlet"})
public class DeleteHistoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // HIS_NO 파라미터 받기
        String hisNoStr = request.getParameter("hisNo");

        if (hisNoStr == null) {
            return;
        }

        int hisNo = Integer.parseInt(hisNoStr);

        // HistoryDAO 를 이용해 해당 행 삭제
        HistoryDAO historyDAO = new HistoryDAO();
        try {
            historyDAO.deleteHistory(hisNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 삭제 완료 메시지 출력 후 GetHistoryServlet 으로 리다이렉트
        response.sendRedirect("GetHistoryServlet?msg=deleted");
    }
}
