package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (fidStr != null) {
            int fid = Integer.parseInt(fidStr);
            db.Furniture db = new db.Furniture();
            bean.FurnitureBean fb = db.getFurnitureById(fid);
            request.setAttribute("furniture", fb);
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
        } else {
            response.sendRedirect("IndexServlet");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }
}