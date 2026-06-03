# AnToanBaoMatProject

Đây là project Java Web bán thực phẩm, sử dụng JSP/Servlet, Maven, MySQL và chạy trên Apache Tomcat.

## Công nghệ sử dụng

* Java
* JSP / Servlet
* Maven
* MySQL
* Apache Tomcat 9
* IntelliJ IDEA

## Cấu trúc project

```text
AnToanBaoMatProject
├── src/main/java
│   ├── controller
│   ├── database
│   ├── model
│   ├── service
│   └── utils
├── src/main/webapp
│   ├── admin
│   ├── assets
│   ├── customer
│   ├── layouts
│   └── WEB-INF
├── pom.xml
└── README.md
```

## Cách chạy project

### 1. Mở project

Mở project bằng IntelliJ IDEA:

```text
File → Open → chọn folder AnToanBaoMatProject
```

Sau đó chờ IntelliJ load Maven.

### 2. Cấu hình database

Project kết nối MySQL trong file:

```text
src/main/java/utils/JDBCUtil.java
```

Thông tin mặc định:

```java
String url = "jdbc:mysql://localhost:3306/projectweb";
String username = "root";
String password = "root";
```

Cần tạo database tên:

```text
projectweb
```

Nếu MySQL không có mật khẩu, sửa:

```java
String password = "";
```

### 3. Cấu hình Tomcat

Project chạy bằng Apache Tomcat 9.

Trong IntelliJ, tạo cấu hình Smart Tomcat:

```text
Run → Edit Configurations → Add New Configuration → Smart Tomcat
```

Cấu hình:

```text
Tomcat Server: đường dẫn tới Tomcat 9
Deployment Directory: src/main/webapp
Context Path: /
Server Port: 8080
```

### 4. Chạy project

Bấm Run và mở trình duyệt:

```text
http://localhost:8080/
```

## Chức năng chính

* Xem trang chủ
* Xem danh sách sản phẩm
* Tìm kiếm sản phẩm
* Đăng ký tài khoản
* Đăng nhập
* Quản lý giỏ hàng
* Quản lý sản phẩm trong trang admin

## Ghi chú

Project cần MySQL và Tomcat 9 để chạy đúng. Không nên dùng Tomcat 10 vì project sử dụng thư viện `javax.servlet`.
