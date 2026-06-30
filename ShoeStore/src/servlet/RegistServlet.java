package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RegistBean;
import db.User;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置编码，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");

        // 3. 准备 Bean 和 DB 类
        RegistBean registBean = new RegistBean();
        User userDb = new User();
        String msg = "";
        
        // 4. 核心逻辑校验
        if (!password.equals(password2)) {
            msg = "两次输入的密码不一致！";
        } else if (userDb.isUserExist(username)) {
            msg = "错误：该用户名已被注册！";
        } else {
            // 校验通过，写入数据库
            boolean isSuccess = userDb.insertUserInfo(username, password, sex, email);
            if (isSuccess) {
                msg = "恭喜，球鞋商城账号注册成功！";
            } else {
                msg = "系统繁忙，注册失败，请稍后再试。";
            }
        }

        // 5. 把提示信息带回页面
        registBean.setMsg(msg);
        request.setAttribute("registBean", registBean);
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }
}