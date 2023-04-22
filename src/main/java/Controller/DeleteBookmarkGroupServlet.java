package Controller;
/*
    @author Nayoon
 */
import DAO.BookmarkGroupDAO;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteBookmarkGroupServlet", urlPatterns = {"/bookmark/DeleteBookmarkGroupServlet"})
public class DeleteBookmarkGroupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // BMG_ID, BMG_NM, BMG_ORDER 파라미터 받기
        String bmgIdStr = request.getParameter("bmgId");

        if (bmgIdStr == null) {
            return;
        }

        int bmgId = Integer.parseInt(bmgIdStr);

        BookmarkGroupDAO bmgDAO = new BookmarkGroupDAO();
        try {
            bmgDAO.deleteBookmarkGroup(bmgId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
