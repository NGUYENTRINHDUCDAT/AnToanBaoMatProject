<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liên Hệ</title>
    <%@ include file="/layouts/common.jsp"%>
    <style>
        .card:hover {
            border: 2px solid;
            border-color: #28a745;
        }

        .discount-percentage {
            position: absolute;
            top: 10px;
            left: 10px;
            background-color: red;
            color: white;
            padding: 5px;
            font-weight: bold;
        }

        .table tr {
            background-color: #efffcf;
            padding: 20px;
        }

        // Style cho thông báo lỗi validation
        .error-msg {
            color: #dc3545;
            font-size: 13px;
            margin-top: 4px;
            display: none;
        }
        .input-error {
            border-color: #dc3545 !important;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="css/styleDangKi.css">

</head>
<body>

<%@ include file="/layouts/header.jsp"%>

<div class="row ms-4 mt-4" style="margin-bottom: 30px">

    <div class="col-lg-6  mt-1">
        <h5 class="text-success">${respone}</h5>
        <div class="container_form">
            <form class="form-container" style="background-color: #efffcf">

                <table>

                    <tr>
                        <th colspan="2" style="text-align: center;">
                            <h3 class="text-success">Fresh Food Thực Phẩm Sạch</h3>
                        </th>
                    </tr>
                    <tr>
                        <td><b>Địa Chỉ:</b></td>
                        <td>Linh Trung, TP. Thủ Đức, TP.HCM</td>

                    </tr>
                    <tr>
                        <td><b>Số Điện Thoại:</b></td>
                        <td>0345778312</td>

                    </tr>
                    <tr>
                        <td><b>Email:</b></td>
                        <td>21130354.st@hcmuaf.edu.vn</td>
                    </tr>
                    <tr>
                        <td><b>Mở Cửa:</b></td>
                        <td>8h - 22h, Từ thứ 2 - chủ nhật</td>
                    </tr>

                </table>

            </form>
        </div>
        <div class="container_form">
            <form id="contactForm" class="form-container" action="../admin?action=addContact&Contact=${respone}" method="post"
                  style="background-color: #efffcf" onsubmit="return validateForm()">
                <input type="hidden" name="action" value="addContact">
                <h3 class="text-success">Liên Hệ Với Chúng Tôi</h3>
                <p>Nếu bạn có thắc mắc gì, có thể gửi yêu cầu cho chúng tôi, và
                    chúng tôi sẽ liên lạc lại với bạn sớm nhất có thể .</p>

                <div class="mb-3">
                    <input type="text" class="form-control" id="full-name" name="name"
                           placeholder="Họ và tên" required="required">
                    <%-- [THÊM MỚI] Thông báo lỗi họ tên --%>
                    <div class="error-msg" id="err-name">Họ và tên không được để trống và phải có ít nhất 3 ký tự.</div>
                </div>
                <div class="mb-3">
                    <input type="tel" class="form-control" id="phone" name="phone"
                           placeholder="Số điện thoại" required="required">
                    <%-- [THÊM MỚI] Thông báo lỗi số điện thoại --%>
                    <div class="error-msg" id="err-phone">Số điện thoại phải có 10 chữ số và bắt đầu bằng 0.</div>
                </div>
                <div class="mb-3">
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="Email" required="required">
                    <%-- [THÊM MỚI] Thông báo lỗi email --%>
                    <div class="error-msg" id="err-email">Email không hợp lệ (ví dụ: abc@gmail.com).</div>
                </div>
                <div class="mb-3">
                    <textarea class="form-control" id="content" name="content"
                              rows="3" placeholder="Nội dung" required="required"></textarea>
                    <%-- [THÊM MỚI] Thông báo lỗi nội dung --%>
                    <div class="error-msg" id="err-content">Nội dung không được để trống và phải có ít nhất 10 ký tự.</div>
                </div>
                <button type="submit" class="btn btn-success" style="width: 100%;">Gửi
                    Thông Tin</button>
            </form>
        </div>

    </div>

    <div class="col-lg-6">

        <div class="map mt-4">
            <iframe
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.2157622266145!2d106.78957711432126!3d10.871187492257226!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175276398969f7b%3A0x9672b7efd0893fc4!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBOw7RuZyBMw6JtIFRwLiBI4buTIENow60gTWluaA!5e0!3m2!1svi!2s!4v1630749508032!5m2!1svi!2s"
                    height="500" width="90%" style="border: 0;" allowfullscreen=""
                    aria-hidden="false" tabindex="0"></iframe>
        </div>
    </div>
</div>
<%@ include file="/layouts/footer.jsp"%>
</body>
<script src="javascript/scriptAjax.js"></script>

<%-- [THÊM MỚI] Script validation hoàn toàn phía client, không ảnh hưởng backend --%>
<script>
    function validateForm() {
        let isValid = true;

        // Validate Họ tên
        const name = document.getElementById('full-name');
        const errName = document.getElementById('err-name');
        if (name.value.trim().length < 3) {
            name.classList.add('input-error');
            errName.style.display = 'block';
            isValid = false;
        } else {
            name.classList.remove('input-error');
            errName.style.display = 'none';
        }

        // Validate Số điện thoại
        const phone = document.getElementById('phone');
        const errPhone = document.getElementById('err-phone');
        const phoneRegex = /^0\d{9}$/;
        if (!phoneRegex.test(phone.value.trim())) {
            phone.classList.add('input-error');
            errPhone.style.display = 'block';
            isValid = false;
        } else {
            phone.classList.remove('input-error');
            errPhone.style.display = 'none';
        }

        // Validate Email
        const email = document.getElementById('email');
        const errEmail = document.getElementById('err-email');
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email.value.trim())) {
            email.classList.add('input-error');
            errEmail.style.display = 'block';
            isValid = false;
        } else {
            email.classList.remove('input-error');
            errEmail.style.display = 'none';
        }

        // Validate Nội dung
        const content = document.getElementById('content');
        const errContent = document.getElementById('err-content');
        if (content.value.trim().length < 10) {
            content.classList.add('input-error');
            errContent.style.display = 'block';
            isValid = false;
        } else {
            content.classList.remove('input-error');
            errContent.style.display = 'none';
        }

        return isValid; // false = chặn submit, true = cho submit
    }
</script>

</html>