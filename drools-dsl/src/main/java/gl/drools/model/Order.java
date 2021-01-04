package gl.drools.model;

import java.util.Date;
import java.util.List;

public class Order {
    private Date orderDate;
    private Double totalPrice;
    private Discount discount;
    private List<Item> items;
    private Boolean isPaid;
    public Boolean getPaid() {
        return isPaid;
    }
    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
