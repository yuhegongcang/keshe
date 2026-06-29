package servlet;

import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 获取注册表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");

        User userDb = new User();
        // 判断用户名是否已存在
        if(userDb.isUserExist(username)){
            request.setAttribute("msg","该用户名已被注册，请更换账号");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
            return;
        }
        // 插入新用户
        boolean flag = userDb.insertUserInfo(username,password,sex,email);
        if(flag){
            // 注册成功跳登录页
            response.sendRedirect("Login.jsp");
        }else{
            request.setAttribute("msg","注册失败，请重新填写信息");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}