package service;

import database.OrderDao;
import model.Order;
import utils.OrderSummary;
import utils.OrderSummaryYear;
import utils.OrderTrend;

import java.util.ArrayList;

public class OrderService {
    // Đối tượng DAO dùng để thao tác trực tiếp với bảng đơn hàng trong database.
    private OrderDao orderDao;

    public OrderService() {
        // Khởi tạo OrderDao để service có thể gọi các hàm thêm, sửa, xóa, truy vấn đơn hàng.
        this.orderDao = new OrderDao();
    }

    public int insert(Order order) {
        // Thêm một đơn hàng mới vào database.
        return orderDao.insert(order);
    }

    public int update(Order order) {
        // Cập nhật thông tin đơn hàng trong database.
        return orderDao.update(order);
    }

    public int delete(Order order) {
        // Xóa đơn hàng khỏi database.
        return orderDao.delete(order);
    }

    public ArrayList<Order> selectAll() {
        // Lấy danh sách tất cả đơn hàng.
        return orderDao.selectAll();
    }

    public Order selectById(int id) {
        // Tìm đơn hàng theo mã đơn hàng.
        return orderDao.selectById(id);
    }

    public ArrayList<Order> selectByCustomerId(int customerId) {
        // Lấy danh sách đơn hàng của một khách hàng theo mã khách hàng.
        return orderDao.selectByCustomerId(customerId);
    }

    public ArrayList<Order> selectByStatusId(int statusId) {
        // Lấy danh sách đơn hàng theo trạng thái đơn hàng.
        // Ví dụ: đang xử lý, đã giao, đã hủy,...
        return orderDao.selectByStatusId(statusId);
    }

    public ArrayList<Order> selectByCustomerIdAndStatusId(int customerId, int statusId) {
        // Lấy danh sách đơn hàng của một khách hàng theo mã khách hàng và trạng thái đơn hàng.
        return orderDao.selectByCustomerIdAndStatusId(customerId, statusId);
    }

    public int selectTotalProductSold(int productId) {
        // Tính tổng số lượng đã bán của một sản phẩm.
        return orderDao.selectTotalProductSold(productId);
    }

    public ArrayList<OrderSummary> getTotalRevenue7Days() {
        // Lấy thống kê doanh thu trong 7 ngày gần nhất.
        return orderDao.getTotalRevenue7Days();
    }

    public double totalRevenue() {
        // Tính tổng doanh thu của toàn bộ đơn hàng.
        return orderDao.totalRevenue();
    }

    public ArrayList<OrderSummaryYear> getTotalRevenueEveryYear() {
        // Lấy thống kê doanh thu theo từng năm.
        return orderDao.getTotalRevenueEveryYear();
    }

    public ArrayList<OrderTrend> selectOrderTrend() {
        // Lấy dữ liệu xu hướng đơn hàng để phục vụ thống kê/báo cáo.
        return orderDao.selectOrderTrend();
    }
}
