package servlet;

import bean.GoodsBean;
import db.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 璋冪敤db灞傛煡璇㈠叏閮ㄥ晢鍝�
    	List<GoodsBean> goodsList = new Goods().findAllGoods();
        
        // 2. 存入请求域
        request.setAttribute("goodsList", goodsList);
        
        // 3. 唯一的转发！确保没有其他 response.sendRedirect 或者 response.getWriter().print
        request.getRequestDispatcher("index.jsp").forward(request, response);
        // 加这行调试代码！
        System.out.println("从数据库查到的商品数量: " + (goodsList != null ? goodsList.size() : "null"));
        
        request.setAttribute("goodsList", goodsList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST璇锋眰澶嶇敤GET閫昏緫
        doGet(request, response);
    }
    
}