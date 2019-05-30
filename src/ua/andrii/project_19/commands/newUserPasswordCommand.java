package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.exception.WrongUserDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class newUserPasswordCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public newUserPasswordCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("newUserPasswordCommand");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");

        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String passwordConfirmation = request.getParameter("password_confirmation");

        try {
            boolean result = adminService.changePassword(user, old_password, new_password, passwordConfirmation);
            if (result) {

                request.setAttribute("message", "Password update is successful");
                return "/admin_page.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to change password");
            }
        } catch (WrongUserDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "/admin_page.jsp";
    }
}