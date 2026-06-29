package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import bean.GoodsBean;
import db.Goods;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    //添加下面这一行
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String keyword = request.getParameter("keyword");
        Goods goodsDao = new Goods();
        ArrayList<GoodsBean> list = goodsDao.searchGoods(keyword);
        request.setAttribute("goodsList", list);
        request.getRequestDispatcher("Index.jsp").forward(request, response);
    }
}