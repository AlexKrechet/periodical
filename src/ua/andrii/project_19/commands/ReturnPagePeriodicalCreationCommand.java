package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReturnPagePeriodicalCreationCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public ReturnPagePeriodicalCreationCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOGGER.debug("ReturnPagePeriodicalCreationCommand()");
        List periodicalsList = adminService.getPeriodicals();
        List publishersList = adminService.getPublishers();
        request.setAttribute("periodical_publisherslist", publishersList);
        request.setAttribute("periodicalslist", periodicalsList);

        return "/periodical_create_new.jsp";
    }
}
