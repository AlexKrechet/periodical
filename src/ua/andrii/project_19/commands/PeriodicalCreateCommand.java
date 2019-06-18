package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PeriodicalCreateCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public PeriodicalCreateCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("PeriodicalCreateCommand()");

        List<Periodical> periodicalsList = adminService.getPeriodicals();
        List<Publisher> publishersList = adminService.getPublishers();

        request.setAttribute("periodical_publisherslist", publishersList);
        request.setAttribute("periodicalslist", periodicalsList);

        return "/periodical.jsp";
    }
}
