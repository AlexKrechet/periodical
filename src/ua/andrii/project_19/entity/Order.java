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
    private BigDecimal totalPrice = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_CEILING);


    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Builder withListPeriodicals(List<PeriodicalOrder> periodicalOrders) {
            newOrder.periodicalOrders = periodicalOrders;
            return this;
        }

        public Builder withUser(User user) {
            newOrder.user = user;
            return this;
        }

        public Builder withTimestamp(Timestamp purchaseDate) {
            newOrder.purchaseDate = purchaseDate;
            return this;
        }

        public Builder withPaidStatus(boolean paid) {
            newOrder.paid = paid;
            return this;
        }

        public Builder withPrice(BigDecimal totalPrice) {
            newOrder.totalPrice = totalPrice;
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }

    public Order() {
        purchaseDate = new Timestamp(new Date().getTime());
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getPresentation() {

        return getId() + " | " + getPurchaseDate() + " | " + getUser().getId() + " | " + getUser().getFullName() + " | " + (isPaid() ? " PAID " : " NOT PAID ") + " | " + getTotalPrice();
    }
}
