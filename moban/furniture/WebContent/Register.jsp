<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.RegistBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册 - 家具销售系统</title>
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
            background: rgba(0, 0, 0, 0.45); z-index: 0; 
        }
        .container {
            position: relative; 
            z-index: 10; 
            margin-top: 100px; 
        }
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
    <div class="container" style="margin-top: 80px;">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center" style="font-size: 24px; font-weight: bold;">家具系统 - 新用户注册</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            RegistBean bean = (RegistBean) request.getAttribute("registBean");
                            if (bean != null && !bean.getMsg().isEmpty()) {
                                String color = bean.getMsg().contains("成功") ? "green" : "red";
                        %>
                            <div class="alert alert-warning text-center" style="color:<%=color%>; font-weight:bold;">
                                <%= bean.getMsg() %>
                            </div>
                        <%
                            }
                        %>
                        <form class="form-horizontal" action="RegistServlet" method="post">
                            <div class="form-group">
                                <label for="username" class="col-sm-3 control-label">用户名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入您的登录名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password2" class="col-sm-3 control-label">确认密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="password2" id="password2" placeholder="请再次输入密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别</label>
                                <div class="col-sm-8">
                                    <label class="radio-inline"><input type="radio" name="sex" value="男" checked> 男</label>
                                    <label class="radio-inline"><input type="radio" name="sex" value="女"> 女</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-3 control-label">电子邮箱</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="请输入邮箱地址">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8 text-center">
                                    <button type="submit" class="btn btn-info" style="width: 45%;">立即注册</button>
                                    <button type="reset" class="btn btn-default" style="width: 45%;">清空</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer text-center">
                        <a href="IndexServlet">返回商城首页</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>