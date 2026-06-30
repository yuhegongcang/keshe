package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserInfoBean;
import db.User;

@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 校验登录状态
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || username.isEmpty()) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        String type = request.getParameter("type");
        User userDb = new User();
        UserInfoBean uiBean = new UserInfoBean();

        // 处理修改请求
        if ("edit".equals(type)) {
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            
            if (userDb.updateUserInfo(username, password, email)) {
                uiBean.setMsg("✅ 个人资料修改成功！");
            } else {
                uiBean.setMsg("⚠️ 修改失败，请重试！");
            }
        }

        // 不管是刚点进来，还是刚改完，都要去数据库查一次最新数据带回页面
        userDb.getUserInfo(uiBean, username);
        request.setAttribute("uiBean", uiBean);
        request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
    }
}