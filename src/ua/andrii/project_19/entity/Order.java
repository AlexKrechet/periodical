package ua.andrii.project_19.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private List<PeriodicalOrder> periodicalOrders;
    private User user;
    private Timestamp purchaseDate;
    private boolean paid;
    private BigDecimal total_price = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_CEILING);


    public Order() {
        purchaseDate = new Timestamp(new Date().getTime());
    }

    public Order(List<PeriodicalOrder> periodicalOrders, User user, Timestamp purchaseDate, boolean paid, BigDecimal total_price) {
        this();
        this.periodicalOrders = periodicalOrders;
        this.user = user;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
        this.total_price = total_price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PeriodicalOrder> getPeriodicalOrders() {
        return periodicalOrders;
    }

    public void setPeriodicalOrders(List<PeriodicalOrder> periodicalOrders) {
        this.periodicalOrders = periodicalOrders;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", periodicalOrders=" + periodicalOrders +
                ", user=" + user +
                ", purchaseDate=" + purchaseDate +
                ", paid=" + paid +
                ", total_price=" + total_price +
                '}';
    }

    public String getPresentation() {

        return getId() + " | " + getPurchaseDate() + " | " + getUser().getId() + " | " + getUser().getFullName() + " | " + (isPaid() ? " PAID " : " NOT PAID ") + " | " + getTotal_price();
    }
}
