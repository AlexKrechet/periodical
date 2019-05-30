package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.StringTokenizer;

public class SelectUserAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);
    private final AdminService adminService;

    public SelectUserAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        logger.debug("SelectUserAdminCommand");

        User user = (User) session.getAttribute("user");
        List usersList = adminService.getUsers();
        request.setAttribute("userslist", usersList);

        User newUser = GetUserFromRequest(request);

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

    private User GetUserFromRequest(HttpServletRequest request) {
        String users = request.getParameter("users.user_selected");
        StringTokenizer t = new StringTokenizer(users, "|");
        String userId = t.nextToken();

        return adminService.getUser(new Long(userId.trim()).longValue());
    }
}
