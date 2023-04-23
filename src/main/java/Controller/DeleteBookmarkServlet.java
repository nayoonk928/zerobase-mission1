package Controller;
/*
    @author Nayoon
 */
import DAO.BookmarkDAO;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteBookmarkServlet", urlPatterns = {"/DeleteBookmarkServlet"})
public class DeleteBookmarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // BM_ID 파라미터 받기
        String bmIdStr = request.getParameter("bmId");

        if (bmIdStr == null) {
            return;
        }

        int bmId = Integer.parseInt(bmIdStr);

        BookmarkDAO bmDAO = new BookmarkDAO();
        try {
            bmDAO.deleteBookmark(bmId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
