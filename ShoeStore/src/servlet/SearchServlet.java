package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ShoeBean;
import db.Shoe;
import util.Common;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取页面传来的搜索类型和关键字
        String type = request.getParameter("type");
        String value = request.getParameter("value");

        Common comm = new Common();
        Shoe shoeDb = new Shoe();
        ArrayList<ShoeBean> arr = shoeDb.getListBeanByType(type, value);


        request.setAttribute("arr", arr);
        request.getRequestDispatcher("Index.jsp").forward(request, response);
    }
}