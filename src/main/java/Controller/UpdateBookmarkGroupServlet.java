package Controller;
/*
    @author Nayoon
 */
import DAO.BookmarkGroupDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateBookmarkGroupServlet", urlPatterns = {"/UpdateBookmarkGroupServlet"})
public class UpdateBookmarkGroupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // BMG_ID, BMG_NM, BMG_ORDER 파라미터 받기
        String bmgIdStr = request.getParameter("bmgId");
        String bmgName = request.getParameter("bmgName");
        String orderStr = request.getParameter("bmgOrder");

        if (bmgIdStr == null || bmgName == null || orderStr == null) {
            return;
        }

        int bmgId = Integer.parseInt(bmgIdStr);
        int order = Integer.parseInt(orderStr);

        // 버튼을 누른 시간 구하기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());

        BookmarkGroupDAO bmgDAO = new BookmarkGroupDAO();
        try {
            boolean isUpdate = bmgDAO.updateBookmarkGroup(bmgId, bmgName, order, formattedDate);
            if (!isUpdate) {
                response.getWriter().write("exists");
                return;
            }
            response.getWriter().write("updated");
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
