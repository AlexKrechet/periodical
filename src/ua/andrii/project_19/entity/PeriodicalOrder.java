package ua.andrii.project_19.entity;

import com.sun.istack.internal.NotNull;

public class PeriodicalOrder {
    private Periodical periodical;
    private int periodicalQuantity;

    public static class Builder {
        private PeriodicalOrder newPeriodicalOrder;

        public Builder() {
            newPeriodicalOrder = new PeriodicalOrder();
        }

        public Builder withPeriodical(@NotNull Periodical periodical) {
            newPeriodicalOrder.periodical = periodical;
            return this;
        }

        public Builder withQuantity(@NotNull int periodicalQuantity) {
            newPeriodicalOrder.periodicalQuantity = periodicalQuantity;
            return this;
        }

        public PeriodicalOrder build() {
            return newPeriodicalOrder;
        }
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public int getPeriodicalQuantity() {
        return periodicalQuantity;
    }

    public void setPeriodicalQuantity(int periodicalQuantity) {
        this.periodicalQuantity = periodicalQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodicalOrder that = (PeriodicalOrder) o;

        if (periodicalQuantity != that.periodicalQuantity) return false;
        return periodical.equals(that.periodical);
    }

    @Override
    public int hashCode() {
        int result = periodical.hashCode();
        result = 31 * result + periodicalQuantity;
        return result;
    }

    @Override
    public String toString() {
        return "PeriodicalOrder{" +
                ", periodical=" + periodical +
                ", periodicalQuantity=" + periodicalQuantity +
                '}';
    }
}
