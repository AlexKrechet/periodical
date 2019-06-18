package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.enums.UserType;
import ua.andrii.project_19.exception.RegistrationException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewUserAdminCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AddNewUserAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("AddNewUserAdminCommand()");
        UserType userType;
        userType = UserType.valueOf(request.getParameter("user_type").toUpperCase());

        //boolean isBlocked = request.getParameter("is_blocked").trim().equals("1") ? true : false;
        boolean isBlocked = true;
        if (request.getParameter("is_blocked") == null) {
            isBlocked = false;
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        try {
            boolean result = adminService.registerUser(login, password, passwordConfirmation, name, surname, isBlocked, userType);
            if (result) {

                request.setAttribute("message", "Registration is successful. " + login + " is added");
                return "/admin_registration.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new user");
            }
        } catch (RegistrationException e) {
            request.setAttribute("error", e.getMessage());
        }
        return "/admin_registration.jsp";
    }
}
