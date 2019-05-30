package ua.andrii.project_19.entity;

import com.sun.istack.internal.NotNull;
import java.math.BigDecimal;

public class Periodical {
    private Long id;
    private String name;
    private Publisher publisher;
    private BigDecimal price = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_CEILING);

    public Periodical(@NotNull String name, @NotNull  Publisher publisher, @NotNull BigDecimal price) {
        this.name = name;
        this.publisher = publisher;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Periodical periodical = (Periodical) o;

        return id.equals(periodical.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher=" + publisher +
                ", price=" + price +
                '}';
    }
    public String getPresentation() {
        return getId() + " | " + getName() + " | " + getPublisher().getId() + " | " + getPublisher().getName() + " | " + getPrice();
    }
}
