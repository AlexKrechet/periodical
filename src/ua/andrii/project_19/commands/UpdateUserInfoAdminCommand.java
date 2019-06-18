package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.exception.WrongUserDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateUserInfoAdminCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(LogOutCommand.class);
    private final AdminService adminService;

    public UpdateUserInfoAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        LOGGER.debug("UpdateUserInfoAdminCommand");

        User user = (User) session.getAttribute("user");
        List<User> usersList = adminService.getUsers();
        request.setAttribute("userslist", usersList);

        String userId = request.getParameter("user_id");
        User newUser = adminService.getUser(new Long(userId.trim()).longValue());


        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        boolean isBlocked = true;
        if (request.getParameter("is_blocked") == null) {
            isBlocked = false;
        }

        try {
            boolean result = adminService.changeUserData(newUser, password, passwordConfirmation, name, surname, isBlocked);
            if (result) {

                request.setAttribute("message", "Changing is successful. " + newUser.getLogin() + " is updated");

                request.setAttribute("user_id", newUser.getId());
                request.setAttribute("login", newUser.getLogin());
                request.setAttribute("password", newUser.getPassword());
                request.setAttribute("password_confirmation", newUser.getPassword());
                request.setAttribute("name", newUser.getName());
                request.setAttribute("surname", newUser.getSurname());


                System.out.println(newUser.getIsBlocked());
                if (newUser.getIsBlocked()) {
                    request.setAttribute("is_blocked", "checked");
                }


                return "/admin_user_update.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new user");
            }
        } catch (WrongUserDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("user_id", newUser.getId());
        request.setAttribute("login", newUser.getLogin());
        request.setAttribute("password", newUser.getPassword());
        request.setAttribute("password_confirmation", newUser.getPassword());
        request.setAttribute("name", newUser.getName());
        request.setAttribute("surname", newUser.getSurname());

        System.out.println(newUser.getIsBlocked());
        if (newUser.getIsBlocked()) {
            request.setAttribute("is_blocked", "checked");
        }

        return "/admin_user_update.jsp";
    }
}
