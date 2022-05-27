<%--
  Created by IntelliJ IDEA.
  User: Linh
  Date: 5/27/2022
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <title>Pitnik Social Network Toolkit</title>
    <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16">

    <link rel="stylesheet" href="css/main.min.css">
    <link rel="stylesheet" href="css/weather-icon.css">
    <link rel="stylesheet" href="css/weather-icons.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/color.css">
    <link rel="stylesheet" href="css/responsive.css">

</head>
<body>
<div class="www-layout">
    <section>
        <div class="gap no-gap signin whitish medium-opacity register">
            <div class="bg-image" style="background-image:url(images/resources/theme-bg.jpg)"></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="big-ad">
                            <figure><img src="images/logo2.png" alt=""></figure>
                            <h1>Welcome to the Pitnik</h1>
                            <p>
                                Pitnik is a social network template that can be used to connect people. use this
                                template for multipurpose social activities like job, dating, posting, bloging and much
                                more. Now join & Make Cool Friends around the world !!!
                            </p>
                            <div class="barcode">
                                <figure><img src="images/resources/Barcode.jpg" alt=""></figure>
                                <div class="app-download">
                                    <span>Download Mobile App and Scan QR Code to login</span>
                                    <ul class="colla-apps">
                                        <li><a title="" href="https://play.google.com/store?hl=en"><img
                                                src="images/android.png" alt="">android</a></li>
                                        <li><a title="" href="https://www.apple.com/lae/ios/app-store/"><img
                                                src="images/apple.png" alt="">iPhone</a></li>
                                        <li><a title="" href="https://www.microsoft.com/store/apps"><img
                                                src="images/windows.png" alt="">Windows</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="we-login-register">
                            <div class="form-title">
                                <i class="fa fa-key"></i>Sign Up
                                <span>Sign Up now and meet the awesome friends around the world.</span>
                            </div>
                            <form class="we-form" method="post">
                                <input type="text" name="account" placeholder="Account">
                                <input type="password" name="passWord" placeholder="PassWord">
                                <input type="text" name="fullName" placeholder="Full Name">
                                <input type="text" name="dateOfBirth" placeholder="Date Of Birth">
                                <br/>
                                <button type="submit" data-ripple="">Register</button>
                            </form>
                            <span>already have an account? <a class="we-account underline" href="#" title="">Sign in</a></span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>

</div>
<script src="js/main.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>
