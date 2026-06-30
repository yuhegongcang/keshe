package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.User;
import bean.RegistBean;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置编码，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        
        // 2. 获取表单数据
        String nameStr = request.getParameter("username");
        String pwdStr = request.getParameter("password");
        String pwdConfirm = request.getParameter("password_confirm");
        String sexStr = request.getParameter("sex");
        String emailStr = request.getParameter("email");
        
        String msg = "";
        String url = "register.jsp"; // 默认跳回注册页
        RegistBean registBean = new RegistBean();
        User user = new User();

        // 3. 业务逻辑校验
        if (nameStr == null || nameStr.trim().isEmpty()) {
            msg = "用户名不能为空！";
        } else if (pwdStr == null || pwdConfirm == null || pwdStr.isEmpty()) {
            msg = "密码不能为空！";
        } else if (!pwdStr.equals(pwdConfirm)) {
            msg = "两次输入的密码不一致！";
        } else if (user.isUserExist(nameStr)) {
            msg = "对不起，该用户名已被注册！";
        } else {
            // 4. 校验通过，写入数据库
            boolean isSuccess = user.insertUserInfo(nameStr, pwdStr, sexStr, emailStr);
            if (isSuccess) {
                msg = "注册成功！请前往登录。";
                // 注册成功后，通常建议跳转到登录页面，这里暂设为跳回注册页显示成功信息
                // url = "login.jsp"; 
            } else {
                msg = "系统繁忙，注册失败，请稍后再试。";
            }
        }

        // 5. 将处理结果打包，转发回前台页面
        registBean.setMsg(msg);
        request.setAttribute("registBean", registBean);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}