<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录 - 家具销售系统</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #f8f9fa;">
<style>
        .bg-slider {
            position: fixed; width: 100%; height: 100%; top: 0; left: 0;
            z-index: -1; list-style: none; margin: 0; padding: 0;
        }
        .bg-slider li {
            position: absolute; width: 100%; height: 100%; top: 0; left: 0;
            background-size: cover; background-position: center; background-repeat: no-repeat;
            opacity: 0; z-index: 0; animation: imageSlider 12s linear infinite; 
        }
        /* 这里的图片路径务必和 welcome.jsp 保持一致（绝对路径） */
        .bg-slider li:nth-child(1) { background-image: url('/furniture/img/3.jpg'); animation-delay: 0s; }
        .bg-slider li:nth-child(2) { background-image: url('/furniture/img/4.jpg'); animation-delay: 4s; }
        .bg-slider li:nth-child(3) { background-image: url('/furniture/img/5.jpg'); animation-delay: 8s; }

        @keyframes imageSlider { 
            0% { opacity: 0; transform: scale(1) translateX(-20px); }
            8% { opacity: 1; transform: scale(1.05) translateX(0px); }
            33% { opacity: 1; transform: scale(1.05) translateX(10px); }
            41% { opacity: 0; transform: scale(1.1) translateX(20px); }
            100% { opacity: 0; }
        }
        .overlay {
            position: fixed; width: 100%; height: 100%; top: 0; left: 0;
            background: rgba(0, 0, 0, 0.45); z-index: 0; /* 遮罩层 */
        }
        
        /* ✨ 极其关键：保证你的登录框浮在背景上面，且加一点玻璃质感 */
        .container {
            position: relative; 
            z-index: 10; 
            margin-top: 100px; /* 让登录框往下沉一点，更好看 */
        }
        /* 如果你的登录框有 panel 或特定 class，可以给它加个半透明白底，效果绝赞 */
        .panel {
            background: rgba(255, 255, 255, 0.9) !important;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            border: none;
        }
    </style>

    <ul class="bg-slider">
        <li></li><li></li><li></li>
    </ul>
    <div class="overlay"></div>
    <div class="container" style="margin-top: 100px;">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center" style="font-size: 24px; font-weight: bold;">家具系统 - 用户登录</h3>
                    </div>
                    <div class="panel-body" style="padding: 30px;">
                        
                        <%-- 接收 Servlet 传回来的报错信息 --%>
                        <%
                            LoginBean bean = (LoginBean) request.getAttribute("loginBean");
                            String defaultName = "";
                            if (bean != null) {
                                defaultName = bean.getNameStr(); // 记住刚才输错密码的账号
                                if(!bean.getMsg().isEmpty()){
                        %>
                            <div class="alert alert-danger text-center" style="font-weight:bold;">
                                <%= bean.getMsg() %>
                            </div>
                        <%
                                }
                            }
                        %>

                        <form class="form-horizontal" action="LoginServlet" method="post">
                            <div class="form-group">
                                <label for="username" class="col-sm-3 control-label">用户名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" value="<%=defaultName%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                                </div>
                            </div>
                            
                            <div class="form-group" style="margin-top: 30px;">
                                <div class="col-sm-offset-3 col-sm-8 text-center">
                                    <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 18px;">登 录</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer text-center">
                        还没有账号？ <a href="Register.jsp">马上注册</a> | <a href="IndexServlet">随便逛逛(免登录)</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>