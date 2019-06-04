package ua.andrii.project_19.entity;

import com.sun.istack.internal.NotNull;

public class Publisher {
    private Long id;
    private String name;

    public static class Builder {
        private Publisher newPublisher;

        public Builder() {
            newPublisher = new Publisher();
        }

        public Builder withName(@NotNull String name) {
            newPublisher.name = name;
            return this;
        }

        public Publisher build() {
            return newPublisher;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publisher publisher = (Publisher) o;
        return id.equals(publisher.id);
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
                '}';
    }

    public String getPresentation() {
        return getId() + " | " + getName();
    }
}
