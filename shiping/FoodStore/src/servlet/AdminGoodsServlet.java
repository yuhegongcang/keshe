package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import db.Goods;
import util.Common;

@WebServlet("/AdminGoodsServlet")
public class AdminGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // 1. 获取 URL 上的 type 参数，用来决定执行什么操作 (add / list / delete)
        String type = request.getParameter("type"); // <--- 改回 type
        
        Goods goodsDb = new Goods();
        Common comm = new Common();
        
        // 2. 用 type 进行分支判断
        if ("add".equals(type)) { // <--- 改回 type
            String id = request.getParameter("id");
            
            if (goodsDb.isGoodsExist(id)) {
                request.setAttribute("msg", "添加失败：商品ID [" + id + "] 已存在！");
                request.getRequestDispatcher("admin/AddFood.jsp").forward(request, response);
                return;
            }
        
            GoodsBean bean = new GoodsBean();
            bean.setId(id);
            bean.setFoodName(request.getParameter("foodName"));
            bean.setPrice(Integer.parseInt(request.getParameter("price")));
            bean.setStock(Integer.parseInt(request.getParameter("stock")));
            
            // 3. 获取表单里下拉框的分类值，这里用 goodsType！
            bean.setType(request.getParameter("goodsType")); // <--- 关键修复：从 goodsType 获取中文分类
            
            bean.setShelfLife(Integer.parseInt(request.getParameter("shelfLife")));
            bean.setProducer(request.getParameter("producer"));
            bean.setIntroduce(request.getParameter("introduce"));
            
            Goods newGoodsDb = new Goods();
            boolean isSuccess = newGoodsDb.addGoods(bean);
            if (isSuccess) {
                request.setAttribute("msg", "太棒了！商品 [" + bean.getFoodName() + "] 成功上架！");
            } else {
                request.setAttribute("msg", "系统异常：商品上架失败。");
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/AddFood.jsp");
            dispatcher.forward(request, response);
        }
        
        else if ("list".equals(type)) { // <--- 改回 type
            Goods listGoodsDb = new Goods();
            ArrayList<GoodsBean> arr = listGoodsDb.getAllGoods();
            request.setAttribute("arr", arr);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/GoodsList.jsp");
            dispatcher.forward(request, response);
        } 
        
        else if ("delete".equals(type)) { // <--- 改回 type
            String id = request.getParameter("id");
            Goods delGoodsDb = new Goods();
            delGoodsDb.deleteGoods(id);
            response.sendRedirect(request.getContextPath() + "/AdminGoodsServlet?type=list");
        }
    }
}