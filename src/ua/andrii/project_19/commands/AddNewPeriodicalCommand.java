package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.exception.WrongPeriodicalDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringTokenizer;

public class AddNewPeriodicalCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AdminService.class);
    public static final String PERIODICAL_PUBLISHERS_LIST = "periodical_publisherslist";
    private final AdminService adminService;

    public AddNewPeriodicalCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("AddNewPeriodicalCommand()");

        String periodical_name = request.getParameter("periodical_name");
        String periodical_price = request.getParameter("periodical_price");
        Publisher periodical_publisher = GetPublisherFromRequest(request);

        try {
            boolean result = adminService.addNewPeriodical(periodical_name, periodical_publisher.getId(), new BigDecimal(periodical_price));
            if (result) {
                List<Periodical> periodicalsList = adminService.getPeriodicals();
                List<Publisher> publishersList = adminService.getPublishers();
                request.setAttribute(PERIODICAL_PUBLISHERS_LIST, publishersList);
                request.setAttribute("periodicallist", periodicalsList);
                request.setAttribute("message", "Periodical creation is successful. " + periodical_name + " is added");
                return "/periodical.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to create a new periodical");
            }
        } catch (WrongPeriodicalDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        List periodicalsList = adminService.getPeriodicals();
        List publishersList = adminService.getPublishers();
        request.setAttribute(PERIODICAL_PUBLISHERS_LIST, publishersList);
        request.setAttribute("periodicalslist", periodicalsList);
        return "/periodical_create_new.jsp";
    }

    private Publisher GetPublisherFromRequest(HttpServletRequest request) {
        String items = request.getParameter("periodicals.publisher_selected");
        StringTokenizer t = new StringTokenizer(items, "|");
        String itemId = t.nextToken();

        return adminService.getPublisher(new Long(itemId.trim()));
    }
}
