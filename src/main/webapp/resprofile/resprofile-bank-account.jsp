<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員資訊</title>
 
    <script src='<c:url value="/assets/js/jQuery-3.6.0.js"/>'></script>
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.4.1/css/simple-line-icons.css" />
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@200&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="font-awesome" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href='<c:url value="/assets/css/resprofile-css/res-bank-account.css" />'>

</head>

<script>
   
    document.addEventListener('DOMContentLoaded', function () {
            function init() {
                $("button.btn_edit").click(function () {
                    if ($("form.bank_info").attr("data-edit") == undefined) { // 進入編輯狀態
                        $("form.bank_info").attr("data-edit", true);
                        $("div.form-control").toggleClass("-none");
                        $("input.form-control").toggleClass("-none");
                        $("select.form-select").attr("hidden", false).attr("data-edit", true);
                        $("button.btn_edit").html("確認");

                    } else {
                        let update_bank_name = ($("input.input_acct_name").val()).trim();
                        let update_bank_acct = ($("input.input_acct").val()).trim();
                        let update_bank_code = ($("select.select_bank_name").find("option:selected").text());

                        if (update_bank_name == "" || update_bank_acct == "" || $("select.form-select").attr("data-edit") == null) {
                            alert("請輸入欄位值!");
                            // document.getElementById("checkAccountName").innerHTML(' <i class="fa-solid fa-circle-exclamation"></i> 內容不可為空白');
                        } else {
                            alert("姓名修改成功");
                            $("div.acct_name").html(update_bank_name).toggleClass("-none");
                            $("input.input_acct_name").val(update_bank_name).toggleClass("-none");
                            $("div.acct").html(update_bank_acct).toggleClass("-none");
                            $("input.input_acct").val(update_bank_acct).toggleClass("-none");
                            $("div.bank_name").text(update_bank_code).toggleClass("-none");
                            $("select.form-select").val(update_bank_code).attr("hidden", true);
                            $("form.bank_info").removeAttr("data-edit");
                            $("button.btn_edit").html("修改");
                    }
                });
            }

            function checkAccountName() {
                $('#checkAccountName').html('');
                let accountName = $('#accountName').val().trim();
                let symbol = ['!', '@', '#', '$', '%', '^', '&', '*'];
                let flag = false;

                if (accountName == '') {
                    $('#checkAccountName').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('輸入內容不得為空!');
                    return;
                } else {
                    for (let i = 0; i < accountName.length; i++) {
                        if (accountName.indexOf(symbol[i]) != -1) {
                            // 有特殊字元
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag) {
                    $('#checkAccountName').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('addclass')).append('不得有特殊符號!');
                } else {
                    $('#checkAccountName').append($('<i>').addClass('fa-solid fa-circle-check').addClass('addclass')).append('輸入正確!');
                }
            }


            function checkAccount() {
                $('#checkAccount').html('');
                let account = $('#account').val().trim();
                let symbol = ['!', '@', '#', '$', '%', '^', '&', '*'];
                let flag = false;

                if (account == '') {
                    $('#checkAccount').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('mr-2')).append('輸入內容不得為空!');
                    return;
                } else {
                    for (let i = 0; i < account.length; i++) {
                        if (account.indexOf(symbol[i]) != -1) {
                            // 有特殊字元
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag) {
                    $('#checkAccount').append($('<i>').addClass('fa-solid fa-circle-exclamation').addClass('addclass')).append('不得有特殊符號!');
                } else {
                    $('#checkAccount').append($('<i>').addClass('fa-solid fa-circle-check').addClass('addclass')).append('輸入正確!');
                }
            }
            init();
            $('#accountName').on('blur', checkAccountName);
            $('#account').on('blur', checkAccount);
        }
    });
</script>

<body>

    <div class="container-scroller">
    <div><jsp:include page="resprofile-sidebar.jsp"/></div>
        <div class="container-fluid page-body-wrapper">
        <jsp:include page="resprofile-header.jsp"/>
            <div class="main-panel">
                <div class="content-wrapper">

                    <div class="card-body">
                        <div class="container mt-5 mb-5">
                            <!-- 表單區塊 -->
                            <form class="jumbotron bank_info" method="post" action="">
                                <div class="form-group row">
                                    <label for="accountName" class="col-sm-12 col-form-label">帳戶人姓名</label>
                                    <div class="col-sm-12">
                                        <div class="form-control acct_name">王曉明</div>
                                        <input id="accountName" type="text" name="accountName"
                                            class="form-control input_acct_name -none" placeholder="請輸入姓名">
                                        <span id="checkAccountName"></span>
                                    </div>

                                    <div class="col-sm-12">
                                        <label for="bankName" class="col-sm-12 col-form-label">銀行名稱及代碼</label>
                                        <div>
                                            <div class="form-control bank_name">004 台灣銀行</div>
                                            <select id="bankName" name="bankName" class="form-select select_bank_name"
                                                aria-label="Default select example" hidden="true">
                                                <option selected>請選擇</option>
                                                <option value="004_臺灣銀行">004_臺灣銀行</option>
                                                <option value="005_土地銀行">005_土地銀行</option>
                                                <option value="006_合作商銀">006_合作商銀</option>
                                                <option value=”007″>007_第一銀行</option>
                                                <option value=”008″>008_華南銀行</option>
                                                <option value=”009″>009_彰化銀行</option>
                                                <option value=”011″>011_上海商業儲蓄銀行</option>
                                                <option value=”012″>012_台北富邦銀行</option>
                                                <option value=”013″>013_國泰世華銀行</option>
                                                <option value=”016″>016_高雄銀行</option>
                                                <option value=”017″>017_兆豐國際商業銀行</option>
                                                <option value=”018″>018_農業金庫</option>
                                                <option value=”021″>021_花旗(台灣)商業銀行</option>
                                                <option value=”025″>025_首都銀行</option>
                                                <option value=”039″>039_澳商澳盛銀行</option>
                                                <option value=”040″>040_中華開發工業銀行</option>
                                                <option value=”050″>050_臺灣企銀</option>
                                                <option value=”052″>052_渣打國際商業銀行</option>
                                                <option value=”053″>053_台中商業銀行</option>
                                                <option value=”054″>054_京城商業銀行</option>
                                                <option value=”072″>072_德意志銀行</option>
                                                <option value=”075″>075_東亞銀行</option>
                                                <option value=”081″>081_匯豐(台灣)商業銀行</option>
                                                <option value=”085″>085_新加坡商新加坡華僑銀行</option>
                                                <option value=”101″>101_大台北銀行</option>
                                                <option value=”102″>102_華泰銀行</option>
                                                <option value=”103″>103_臺灣新光商銀</option>
                                                <option value=”104″>104_台北五信</option>
                                                <option value=”106″>106_台北九信</option>
                                                <option value=”108″>108_陽信商業銀行</option>
                                                <option value=”114″>114_基隆一信</option>
                                                <option value=”115″>115_基隆二信</option>
                                                <option value=”118″>118_板信商業銀行</option>
                                                <option value=”119″>119_淡水一信</option>
                                                <option value=”120″>120_淡水信合社</option>
                                                <option value=”124″>124_宜蘭信合社</option>
                                                <option value=”127″>127_桃園信合社</option>
                                                <option value=”130″>130_新竹一信</option>
                                                <option value=”132″>132_新竹三信</option>
                                                <option value=”146″>146_台中二信</option>
                                                <option value=”147″>147_三信商業銀行</option>
                                                <option value=”158″>158_彰化一信</option>
                                                <option value=”161″>161_彰化五信</option>
                                                <option value=”162″>162_彰化六信</option>
                                                <option value=”163″>163_彰化十信</option>
                                                <option value=”165″>165_鹿港信合社</option>
                                                <option value=”178″>178_嘉義三信</option>
                                                <option value=”179″>179_嘉義四信</option>
                                                <option value=”188″>188_台南三信</option>
                                                <option value=”204″>204_高雄三信</option>
                                                <option value=”215″>215_花蓮一信</option>
                                                <option value=”216″>216_花蓮二信</option>
                                                <option value=”222″>222_澎湖一信</option>
                                                <option value=”223″>223_澎湖二信</option>
                                                <option value=”224″>224_金門信合社</option>
                                                <option value=”512″>512_雲林區漁會</option>
                                                <option value=”515″>515_嘉義區漁會</option>
                                                <option value=”517″>517_南市區漁會</option>
                                                <option value=”518″>518_南縣區漁會</option>
                                                <option value=”520″>520_小港區漁會；高雄區漁會</option>
                                                <option value=”521″>521_彌陀區漁會；永安區漁會；興達港區漁會；林園區漁會</option>
                                                <option value=”523″>523_東港漁會；琉球區漁會；林邊區漁會</option>
                                                <option value=”524″>524_新港區漁會</option>
                                                <option value=”525″>525_澎湖區漁會</option>
                                                <option value=”605″>605_高雄市農會</option>
                                                <option value=”612″>612_豐原市農會；神岡鄉農會</option>
                                                <option value=”613″>613_名間農會</option>
                                                <option value=”614″>614_彰化地區農會</option>
                                                <option value=”616″>616_雲林地區農會</option>
                                                <option value=”617″>617_嘉義地區農會</option>
                                                <option value=”618″>618_台南地區農會</option>
                                                <option value=”619″>619_高雄地區農會</option>
                                                <option value=”620″>620_屏東地區農會</option>
                                                <option value=”621″>621_花蓮地區農會</option>
                                                <option value=”622″>622_台東地區農會</option>
                                                <option value=”624″>624_澎湖農會</option>
                                                <option value=”625″>625_台中市農會</option>
                                                <option value=”627″>627_連江縣農會</option>
                                                <option value=”700″>700_中華郵政</option>
                                                <option value=”803″>803_聯邦商業銀行</option>
                                                <option value=”805″>805_遠東銀行</option>
                                                <option value=”806″>806_元大銀行</option>
                                                <option value=”807″>807_永豐銀行</option>
                                                <option value=”808″>808_玉山銀行</option>
                                                <option value=”809″>809_萬泰銀行</option>
                                                <option value=”810″>810_星展銀行</option>
                                                <option value=”812″>812_台新銀行</option>
                                                <option value=”814″>814_大眾銀行</option>
                                                <option value=”815″>815_日盛銀行</option>
                                                <option value=”816″>816_安泰銀行</option>
                                                <option value=”822″>822_中國信託</option>
                                                <option value=”901″>901_大里市農會</option>
                                                <option value=”903″>903_汐止農會</option>
                                                <option value=”904″>904_新莊農會</option>
                                                <option value=”910″>910_財團法人農漁會聯合資訊中心</option>
                                                <option value=”912″>912_冬山農會</option>
                                                <option value=”916″>916_草屯農會</option>
                                                <option value=”922″>922_台南市農會</option>
                                                <option value=”928″>928_板橋農會</option>
                                                <option value=”951″>951_北農中心</option>
                                                <option value=”954″>954_中南部地區農漁會</option>
                                            </select>
                                        </div>
                                    </div>

                                    <label for="account" class="col-sm-12 col-form-label">銀行帳號</label>
                                    <div class="col-sm-12">
                                        <div class="form-control acct">3200 2921 3421</div>
                                        <input id="account" type="text" name="account"
                                            class="form-control input_acct -none" placeholder="請輸入帳號">
                                        <span id="checkAccount"></span>
                                    </div>
                                </div>
                            </form>
                            <div class="d-flex justify-content-end">
                                <button type="button" class="btn btn_edit btn-outline-dark mf-bdr-15">修改</button>
                                <button type="submit" class="btn btn-outline-dark mf-bdr-15 ml-2">送出
                                    <i class="fa-solid fa-arrow-right"></i>
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                
                <jsp:include page="resprofile-footer.jsp"/>
            </div>
        </div>
    </div>
</body>

</html>