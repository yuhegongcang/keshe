<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎来到现代家具商城</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
    
    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        /* ============================================================================== */
        /* ✨ 核心：纯 CSS 全屏背景图平滑换图动画（从左到右滑动+淡入淡出） */
        /* ============================================================================== */
        .bg-slider {
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            z-index: -1; /* 垫在最底层 */
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .bg-slider li {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            opacity: 0; /* 默认隐藏 */
            z-index: 0;
            /* 12秒一个循环，平滑过渡 */
            animation: imageSlider 12s linear infinite; 
        }

        /* 这里的图片地址你可以换成你 img 文件夹下的精美家具图，比如 3.jpg, 4.jpg 等 */
        .bg-slider li:nth-child(1) { 
            background-image: url('img/3.jpg'); 
            animation-delay: 0s;
        }
        .bg-slider li:nth-child(2) { 
            background-image: url('img/4.jpg'); 
            animation-delay: 4s; /* 4秒后显示第二张 */
        }
        .bg-slider li:nth-child(3) { 
            background-image: url('img/5.jpg'); 
            animation-delay: 8s; /* 8秒后显示第三张 */
        }

        /* 动画控制：结合了淡入淡出(opacity)和从左到右微调位移(transform) */
        @keyframes imageSlider { 
            0% {
                opacity: 0;
                transform: scale(1) translateX(-20px);
            }
            8% {
                opacity: 1; /* 淡入完成 */
                transform: scale(1.05) translateX(0px); /* 向右平滑移动并微放大 */
            }
            33% {
                opacity: 1;
                transform: scale(1.05) translateX(10px);
            }
            41% {
                opacity: 0; /* 淡出完成 */
                transform: scale(1.1) translateX(20px);
            }
            100% {
                opacity: 0;
            }
        }

        /* ============================================================================== */
        /* 遮罩层：让背景图变暗一点，确保前台文字清晰可见 */
        /* ============================================================================== */
        .overlay {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background: rgba(0, 0, 0, 0.45); /* 45% 的黑色半透明遮罩 */
            z-index: 1;
        }

        /* ============================================================================== */
        /* 顶部导航栏美化 */
        /* ============================================================================== */
        .custom-navbar {
            background: rgba(0, 0, 0, 0.6) !important; /* 半透明玻璃质感 */
            border: none !important;
            border-radius: 0 !important;
            z-index: 10;
            position: relative;
        }
        .custom-navbar .navbar-brand, .custom-navbar .navbar-nav > li > a {
            color: #fff !important;
            font-weight: bold;
            transition: all 0.3s;
        }
        .custom-navbar .navbar-nav > li > a:hover {
            color: #f0ad4e !important; /* 悬浮变成温馨的金黄色 */
            background: transparent !important;
        }

        /* ============================================================================== */
        /* 居中欢迎语与按钮 */
        /* ============================================================================== */
        .welcome-container {
            position: absolute;
            top: 45%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 5;
            text-align: center;
            color: white;
            width: 80%;
        }
        .welcome-title {
            font-size: 48px;
            font-weight: 700;
            letter-spacing: 4px;
            margin-bottom: 20px;
            text-shadow: 0 4px 10px rgba(0,0,0,0.7);
        }
        .welcome-subtitle {
            font-size: 20px;
            font-weight: 300;
            color: #eee;
            margin-bottom: 40px;
            text-shadow: 0 2px 5px rgba(0,0,0,0.7);
        }
        .action-btns .btn {
            padding: 12px 35px;
            font-size: 18px;
            font-weight: bold;
            border-radius: 30px;
            margin: 0 15px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.4);
            transition: all 0.3s;
        }
        .action-btns .btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(0,0,0,0.6);
        }
    </style>
</head>
<body>

    <ul class="bg-slider">
        <li></li>
        <li></li>
        <li></li>
    </ul>

    <div class="overlay"></div>

    <nav class="navbar navbar-default custom-navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="welcome.jsp">🛋️ 现代家具商城</a>
            </div>
            <ul class="nav navbar-nav">
   				 <li><a href="IndexServlet">商品总览</a></li>
   				 <li><a href="#">热销推荐</a></li>
   				 <li><a href="#">关于我们</a></li>
			</ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="UserCenter.jsp"><span class="glyphicon glyphicon-user"></span> 个人中心</a></li>
            </ul>
        </div>
    </nav>

    <div class="welcome-container">
        <h1 class="welcome-title">匠心独运 · 筑就理想家园</h1>
        <p class="welcome-subtitle">欢迎来到现代家具商城，在这里，每一件家具都是对美好生活的一次优雅致敬。</p>
        
        <div class="action-btns">
            <a href="Login.jsp" class="btn btn-warning">立即登录</a>
            <a href="Register.jsp" class="btn btn-default">注册新账户</a>
        </div>
    </div>

</body>
</html>