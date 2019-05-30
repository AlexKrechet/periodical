package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.enums.UserType;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientRegistrationCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("ClientRegistrationCommand()");
        User user = (User) request.getSession().getAttribute("user");

        if ((user != null) && (user.getUserType() == UserType.ADMIN)) {
            return "/admin_registration.jsp";
        }
        return "/client_registration.jsp";
    }
}
