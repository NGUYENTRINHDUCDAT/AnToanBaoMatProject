<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <%@ include file="layouts/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="assets/css/dangNhap.css">
    <script src="assets/javascript/facebookAPI.js"></script>
</head>
<body>
<%@ include file="layouts/header.jsp" %>

<div class="container_form content">
    <form action="customer?action=confirmKeyPairs"  method="post" class="form-container">
        <h1 class="text-center mb-3 text-success ">
            Nhập Khóa Mới <a href="#" class="logo-link"> <img
                style="width: 60px; height: 60px" src="assets/images/rau.png" alt="Logo"
                class="logo-image">
        </a>
        </h1>

        <p style="text-align: center; color: red">${confirm_success}</p>
        <label for="publicKey">Public Key: </label>
        <div style="text-align: center">
            <textarea id="publicKey" name="publicKey" rows="4" cols="50" required>

            </textarea>
        </div>
        <label for="sign">Vui lòng tạo chữ kí với văn bản sau để xác thực khóa : </label>
        <div style="text-align: center">
            <textarea id="text_test" rows="4" cols="50" readonly>
                ${text_test_key}
            </textarea>
        </div>
        <label for="sign">Chữ kí : </label>
        <div style="text-align: center">
            <textarea id="sign"  name="sign" rows="4" cols="50" required>

            </textarea>
        </div>
        <button type="submit"  style="height: 47.33px" class="btn btn-primary mb-2 margin-button"> Xác nhận
    </button>
    </form>
</div>