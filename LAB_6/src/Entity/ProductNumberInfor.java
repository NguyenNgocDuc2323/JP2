package Entity;

import java.time.LocalDate;

public class ProductNumberInfor {
    private int id;
    private Product product;
    private int clickNumber;
    private int addToCartNumber;
    private int checkOutNumber;
    private LocalDate dateTime;
    public ProductNumberInfor() {;}

    public ProductNumberInfor(int id, Product product, int clickNumber, int addToCartNumber, int checkOutNumber, LocalDate dateTime) {
        this.id = id;
        this.product = product;
        this.clickNumber = clickNumber;
        this.addToCartNumber = addToCartNumber;
        this.checkOutNumber = checkOutNumber;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(int clickNumber) {
        this.clickNumber = clickNumber;
    }

    public int getAddToCartNumber() {
        return addToCartNumber;
    }

    public void setAddToCartNumber(int addToCartNumber) {
        this.addToCartNumber = addToCartNumber;
    }

    public int getCheckOutNumber() {
        return checkOutNumber;
    }

    public void setCheckOutNumber(int checkOutNumber) {
        this.checkOutNumber = checkOutNumber;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ProductNumberInfor{" +
                "id=" + id +
                ", product=" + product +
                ", clickNumber=" + clickNumber +
                ", addToCartNumber=" + addToCartNumber +
                ", checkOutNumber=" + checkOutNumber +
                ", dateTime=" + dateTime +
                '}';
    }
}
