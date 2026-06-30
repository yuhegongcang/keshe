package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        // 如果未登录，强制跳回登录页
        if (username == null || username.isEmpty()) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserInfoBean uiBean = new UserInfoBean();
        User user = new User();
        String type = request.getParameter("type");
        String url = "UserInfo.jsp";

        // 如果是修改密码操作 (对应页面表单的 type="edit")
        if ("edit".equals(type)) {
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String email = request.getParameter("email");

            if (!password.equals(password2)) {
                uiBean.setMsg("密码不一致，请重试！");
            } else {
                user.updateUserInfo(uiBean, username, password, email); // 调用底层更新方法
            }
        }

        // 无论是否修改，最后都重新获取一遍最新用户信息用于页面显示
        user.getUserInfo(uiBean, username);
        request.setAttribute("uibean", uiBean);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}