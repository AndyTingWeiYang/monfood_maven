<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商家資訊</title>
    <script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script>
    <style>
        .gradient-custom {
            background: rgb(210, 196, 178)
        }

        .mask-custom {
            background: rgba(24, 24, 16, .2);
            border-radius: 2em;
            backdrop-filter: blur(15px);
            border: 2px solid rgba(255, 255, 255, 0.05);
            background-clip: padding-box;
            box-shadow: 10px 10px 10px rgba(46, 54, 68, 0.03);
        }

        .mouse-cursor {
            cursor: pointer;
        }
    </style>


</head>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        function init() {
            $('.mouse-cursor').each(function (index, element) {
                let eleObj = $(element);
                eleObj.click(function () {
                    console.log('index = ' + index); // 0
                    console.log('data-mail-id = ' + eleObj.data('mail-id')); // 3
                    $('.card-body li').eq(eleObj.data('mail-id')).remove();
                    $('.message').eq(index).html('');
                })
            });
        }
        init();
    });
</script>
<body>
    <div class="container-scroller">
    <div><jsp:include page="resprofile-sidebar.jsp" /></div>
        <div class="container-fluid page-body-wrapper">
        <jsp:include page="resprofile-header.jsp" />
            <div class="main-panel">
                <div class="mf-content-wrapper">
                    <section class="gradient-custom" style="height: 100vh; overflow-y: auto;">
                        <div class="container py-5">
                            <div class="row">
                                <div class="col-md-6 col-lg-5 col-xl-5 mb-4 mb-md-0">
                                    <div class="card mask-custom">
                                        <div class="card-body">
                                            <ul class="list-unstyled mb-0">
                                                <li class="p-2 border-bottom"
                                                    style="border-bottom: 1px solid rgba(255,255,255,.3) !important;">
                                                    <a href="#!" class="d-flex justify-content-between">
                                                        <div class="d-flex flex-row">
                                                            <i
                                                                class="fa-regular fa-bell rounded-circle d-flex align-self-center me-3 shadow-1-strong"></i>
                                                            <div class="pt-1">
                                                                <p class="fw-bold mb-0">系統通知</p>
                                                                <p class="small">本月折價券代碼 : 94666</p>
                                                            </div>
                                                        </div>
                                                        <div class="pt-1">
                                                            <p class="small mb-1">Yesterday</p>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="p-2 border-bottom"
                                                    style="border-bottom: 1px solid rgba(255,255,255,.3) !important;">
                                                    <a href="#!" class="d-flex justify-content-between">
                                                        <div class="d-flex flex-row">
                                                            <i
                                                                class="fa-regular fa-bell rounded-circle d-flex align-self-center me-3 shadow-1-strong"></i>
                                                            <div class="pt-1">
                                                                <p class="fw-bold mb-0">Amy Moss</p>
                                                                <p class="small">Lorem ipsum dolor sit.</p>
                                                            </div>
                                                        </div>
                                                        <div class="pt-1">
                                                            <p class="small mb-1">Yesterday</p>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="p-2 border-bottom"
                                                    style="border-bottom: 1px solid rgba(255,255,255,.3) !important;">
                                                    <a href="#!" class="d-flex justify-content-between">
                                                        <div class="d-flex flex-row">
                                                            <i
                                                                class="fa-regular fa-bell rounded-circle d-flex align-self-center me-3 shadow-1-strong"></i>
                                                            <div class="pt-1">
                                                                <p class="fw-bold mb-0">Kate Moss</p>
                                                                <p class="small">Lorem ipsum dolor sit.</p>
                                                            </div>
                                                        </div>
                                                        <div class="pt-1">
                                                            <p class="small mb-1">Yesterday</p>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="p-2 border-bottom"
                                                    style="border-bottom: 1px solid rgba(255,255,255,.3) !important;">
                                                    <a href="#!" class="d-flex justify-content-between">
                                                        <div class="d-flex flex-row">
                                                            <i
                                                                class="fa-regular fa-bell rounded-circle d-flex align-self-center me-3 shadow-1-strong"></i>
                                                            <div class="pt-1">
                                                                <p class="fw-bold mb-0">Kate Moss</p>
                                                                <p class="small">Lorem ipsum dolor sit.</p>
                                                            </div>
                                                        </div>
                                                        <div class="pt-1">
                                                            <p class="small mb-1">Yesterday</p>
                                                        </div>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-7 col-xl-7">
                                    <ul class="list-unstyled message">
                                        <li class="d-flex justify-content-between mb-4">
                                            <div class="card mask-custom">
                                                <div class="card-header d-flex justify-content-between p-3"
                                                    style="border-bottom: 1px solid rgba(255,255,255,.3);">
                                                    <p class="fw-bold mb-0">訊息內容</p>
                                                    <i class="fa-regular fa-trash-can mouse-cursor"
                                                        data-mail-id="1"></i>

                                                </div>
                                                <div class="card-body">
                                                    <p class="mb-0">
                                                        系統訊息通知 : 本月平台將提供94666優惠代碼給使用者使用
                                                        
                                                    </p>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <jsp:include page="resprofile-footer.jsp" />
            </div>
        </div>

    </div>
</body>

</html>