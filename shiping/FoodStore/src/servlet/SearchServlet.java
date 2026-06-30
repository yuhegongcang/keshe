package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Goods;
import bean.GoodsBean;
import util.Common;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 强制统一字符编码，防止 POST 请求乱码
    	// 1. 强制统一字符编码
        request.setCharacterEncoding("UTF-8");
        
        String value = request.getParameter("value");
        String type = request.getParameter("type");

        System.out.println("DEBUG: 检索请求 - type=" + type + ", value=" + value);

        if (value == null) {
            value = "";
        }

        // 2. 调用数据库层获取商品列表
        Goods goods = new Goods();
        ArrayList<GoodsBean> arr = new ArrayList<>();
        
        // 【核心修改】：精准分流“分类检索”和“模糊检索”
        if ("class".equals(type)) {
            // == 场景 A：点击导航栏的分类 ==
            if (value.isEmpty() || "all".equals(value)) {
                arr = goods.getAllGoods(); // 全部商品
            } else {
                arr = goods.getListBeanByType(type, value); // 按分类查
            }
        } 
        else if ("foodName".equals(type)) {
            // == 场景 B：使用顶部搜索框搜名字 ==
            if (value.isEmpty()) {
                arr = goods.getAllGoods(); // 如果什么都没输直接点搜索，就显示全部
            } else {
                arr = goods.getGoodsByFuzzyName(value); // 【调用刚刚写的模糊查询方法】
            }
        } 
        else {
            // 兜底方案
            arr = goods.getAllGoods();
        }

        // 3. 将结果放入 request
        request.setAttribute("arr", arr);

        // 4. 转发到首页
        RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
        System.out.println("DEBUG: 查询到的商品数量为: " + arr.size());
        dispatcher.forward(request, response);
    }
}