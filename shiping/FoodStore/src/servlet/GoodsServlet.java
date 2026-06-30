package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Goods;
import bean.GoodsBean;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        
        String url = "Index.jsp"; // 默认防错跳转回首页
        
        if ("info".equals(type) && id != null) {
            // 调用模型层查询单条食品数据
            Goods goodsDb = new Goods();
            GoodsBean goodsBean = goodsDb.getGoodsById(id);
            
            // 将查询结果存入 request 中，起名为 goodsBean
            request.setAttribute("goodsBean", goodsBean);
            url = "FoodInfo.jsp"; // 转发到详情页
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}