package ua.andrii.project_19.entity;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.enums.UserType;

public class Client extends User {

    public Client(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname, @NotNull Boolean isBlocked
    ) {
        super(login, password, name, surname, isBlocked);
    }

    public Client(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname) {
        super(login, password, name, surname);
    }

    @Override
    public UserType getUserType() {
        return UserType.CLIENT;
    }

    @Override
    public String toString() {
        String isBlockedText =  getIsBlocked() ?  "Denied" : "Allowed";
        return "Client{" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", Access='" + isBlockedText +
                '}';
    }
}
