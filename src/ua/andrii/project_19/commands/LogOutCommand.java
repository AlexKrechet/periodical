package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        LOGGER.debug("LogOutCommand");
        session.removeAttribute("user");
        session.invalidate();
        return "/index.jsp";
    }
}
