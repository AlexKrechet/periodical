package ua.andrii.project_19.entity;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.enums.UserType;

import java.io.Serializable;

public abstract class User implements Serializable {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean isBlocked;

    public User(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname, @NotNull boolean isBlocked) {
        this(login, password, name, surname);
        this.isBlocked = isBlocked;
    }

    public User(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(@NotNull String login, @NotNull String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getFullName() {
        return surname + " " + name;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public abstract UserType getUserType();

    public static User getUser(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname, boolean isBlocked, UserType userType) {
        if (userType == UserType.ADMIN) {
            return new Admin(login, password, name, surname, isBlocked);
        } else {
            return new Client(login, password, name, surname, isBlocked);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        String isBlockedText = isBlocked ? "Banned" : "Allowed";
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Access='" + isBlockedText +
                '}';
    }

    public String getPresentation() {
        return getId() + " | " + getFullName();
    }
}
