package utils;

import model.Product;

public class TrendProduct {
    private Product product;
    private int quantityFirstMonth;
    private int quantitySecondMonth;
    private int quantityThirdMonth;
    private String trend;

    public TrendProduct(Product product, int quantityFirstMonth, int quantitySecondMonth, int quantityThirdMonth) {
        this.product = product;
        this.quantityFirstMonth = quantityFirstMonth;
        this.quantitySecondMonth = quantitySecondMonth;
        this.quantityThirdMonth = quantityThirdMonth;

        // So sánh số lượng bán ra trong 3 tháng để xác định xu hướng sản phẩm.
        // Nếu số lượng giảm dần qua các tháng thì xu hướng là "Đi Xuống".
        if(quantityFirstMonth >= quantitySecondMonth && quantitySecondMonth >= quantityThirdMonth){
            this.trend = "Đi Xuống";

        // Nếu số lượng tăng dần qua các tháng thì xu hướng là "Đi Lên".
        } else if (quantityThirdMonth >= quantitySecondMonth && quantitySecondMonth >= quantityFirstMonth) {
            this.trend = "Đi Lên";
        }else{

        // Các trường hợp còn lại được xem là xu hướng ổn định/không rõ ràng.
            this.trend = "Đi Ngang";
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityFirstMonth() {
        return quantityFirstMonth;
    }

    public void setQuantityFirstMonth(int quantityFirstMonth) {
        this.quantityFirstMonth = quantityFirstMonth;
    }

    public int getQuantitySecondMonth() {
        return quantitySecondMonth;
    }

    public void setQuantitySecondMonth(int quantitySecondMonth) {
        this.quantitySecondMonth = quantitySecondMonth;
    }

    public int getQuantityThirdMonth() {
        return quantityThirdMonth;
    }

    public void setQuantityThirdMonth(int quantityThirdMonth) {
        this.quantityThirdMonth = quantityThirdMonth;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}
