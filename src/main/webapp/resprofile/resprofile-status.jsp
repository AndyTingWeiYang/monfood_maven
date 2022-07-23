<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商家資訊</title>
    <link rel="stylesheet" href="<c:url value='/assets/vendors/file-input/css/bootstrap-icons.min.css' />" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link href="<c:url value='/assets/vendors/file-input/css/fileinput.min.css' />" media="all" rel="stylesheet" type="text/css" />
    <script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/buffer.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/vendors/file-input/piexif.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/vendors/file-input/sortable.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/vendors/file-input/bootstrap.bundle.min.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/fileinput.min.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/theme.min.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/zh-TW.js' />"></script>

    <style>
        .kv-avatar .krajee-default.file-preview-frame,
        .kv-avatar .krajee-default.file-preview-frame:hover {
            margin: 0;
            padding: 0;
            border: none;
            box-shadow: none;
            text-align: center;
        }

        .kv-avatar {
            display: inline-block;
        }

        .kv-avatar .file-input {
            display: table-cell;
            width: 213px;
        }

        .kv-reqd {
            color: red;
            font-family: monospace;
            font-weight: normal;
        }
    </style>


</head>
<script>
    $(document).ready(function () {
            function init() {
                // fix file-input conflict to data-bs-toggle="collapse"
                $('.accordion-button').each(function (index, element) {
                    $(this).unbind('click').click(function () {
                        let bsTarget = $(element).data('bs-target');
                        $(bsTarget).slideToggle();

                        $(this).toggleClass('collapsed');
                    });
                });
            }


            function fileSingleUpload() {
                let btnCust = '<button type="button" class="btn btn-secondary" title="Add picture tags" ' +
                    'onclick="alert(\'Call your custom code here.\')">' +
                    '<i class="fa-solid fa-bullhorn"></i>' +
                    '</button>';

                $(".mf-avatar").fileinput({
                    overwriteInitial: true,
                    showClose: false,
                    showCaption: false,
                    browseLabel: '',
                    removeLabel: '',
                    browseIcon: '<i class="fa-regular fa-folder-open"></i>',
                    removeIcon: '<i class="fa-regular fa-trash-can"></i>',
                    removeTitle: 'Cancel or reset changes',
                    elErrorContainer: '#kv-avatar-errors-1',
                    msgErrorClass: 'alert alert-block alert-danger',
                    defaultPreviewContent: '<img src="<c:url  value='/assets/images/image-regular.svg' />" alt="Your Avatar">',
                    layoutTemplates: { main2: '{preview} ' + btnCust + ' {remove} {browse}' },
                    theme: "fa6",
                    language: "zh-TW"
                });
            }
            init();
            fileSingleUpload();
    }); 
</script>

<body>
    <div class="container-scroller">
        <div><jsp:include page="resprofile-sidebar.jsp" /></div>
        <div class="container-fluid page-body-wrapper">
			<jsp:include page="resprofile-header.jsp" />        
            <div class="main-panel">
                <div class="mf-content-wrapper">

                    <!-- 表單區塊 -->
                    <form class="jumbotron" method="post" action="" enctype="multipart/form-data">
                        <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingOne">
                                    <button class="accordion-button" type="button" data-bs-target="#collapseOne"
                                        aria-expanded="true" aria-controls="collapseOne">
                                        請上傳身分證照片
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse show"
                                    aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        <div class="row">
                                            <div class="col-sm-4 text-center">
                                                <div class="kv-avatar">
                                                    <div class="file-loading">
                                                        <input id="avatar-1" class="mf-avatar" name="avatar-1"
                                                            type="file" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingTwo">
                                    <button class="accordion-button collapsed" type="button"
                                        data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        請上傳餐廳照片
                                    </button>
                                </h2>
                                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                                    data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        <div class="row">
                                            <div class="col-sm-4 text-center">
                                                <div class="kv-avatar">
                                                    <div class="file-loading">
                                                        <input id="avatar-1" class="mf-avatar" name="avatar-1"
                                                            type="file" required>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingThree">
                                    <button class="accordion-button collapsed" type="button"
                                        data-bs-target="#collapseThree" aria-expanded="false"
                                        aria-controls="collapseThree">
                                        請上傳餐廳營業證
                                    </button>
                                </h2>
                                <div id="collapseThree" class="accordion-collapse collapse"
                                    aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                                    <div class="accordion-body">

                                        <div class="row">
                                            <div class="col-sm-4 text-center">
                                                <div class="kv-avatar">
                                                    <div class="file-loading">
                                                        <input id="avatar-1" class="mf-avatar" name="avatar-1"
                                                            type="file" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>
                    <div class="d-flex justify-content-end mt-5">
                        <button type="button" id="btnCheck" class="btn btn-outline-dark mf-bdr-15">確定</button>
                        <button type="button" id="btnCancel" class="btn btn-outline-dark mf-bdr-15 ml-2">取消
                        </button>
                    </div>
                </div>
                <jsp:include page="resprofile-footer.jsp"/>
            </div>
        </div>

    </div>
</body>

</html>