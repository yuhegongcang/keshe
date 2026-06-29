package servlet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.FurnitureBean;
import db.Furniture;
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        db.Furniture furnitureDb = new db.Furniture();
        java.util.ArrayList<bean.FurnitureBean> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = furnitureDb.searchFurniture(keyword);
        } else {
            list = furnitureDb.getAllFurniture(); 
        }
        request.setAttribute("fList", list);
        request.getRequestDispatcher("Index.jsp").forward(request, response);
        }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}