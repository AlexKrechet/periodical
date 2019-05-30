package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPasswordChangeCommand extends Command{

    private static final Logger logger = Logger.getLogger(AdminService.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("UserPasswordChangeCommand()");
        User user = (User) request.getSession().getAttribute("user");

        return "/user_edit_password.jsp";
    }
}
