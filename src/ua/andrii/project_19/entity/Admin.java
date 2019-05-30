package ua.andrii.project_19.entity;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.enums.UserType;

public class Admin extends User {

    public Admin(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname, @NotNull boolean isBlocked) {
        super(login, password, name, surname, isBlocked);
    }

    public Admin(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname) {
        super(login, password, name, surname);
    }

    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public String toString() {
        String isBlockedText =  getIsBlocked() ?  "Denied" : "Allowed";
        return "Admin{" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", Access='" + isBlockedText +
                '}';
    }
}