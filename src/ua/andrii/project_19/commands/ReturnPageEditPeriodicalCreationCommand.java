package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReturnPageEditPeriodicalCreationCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public ReturnPageEditPeriodicalCreationCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOGGER.debug("ReturnPageEditPeriodicalCreationCommand()");
        List<Periodical> periodicalsList = adminService.getPeriodicals();
        List<Publisher> publishersList = adminService.getPublishers();

        Periodical newPeriodical = GetPeriodicalFromRequest(request);
        publishersList = deletePublisherFromList(publishersList, newPeriodical.getPublisher());
        publishersList.add(0, newPeriodical.getPublisher());

        request.setAttribute("periodical_publisherslist", publishersList);
        request.setAttribute("periodicalslist", periodicalsList);

        request.setAttribute("periodical_id", newPeriodical.getId());
        request.setAttribute("periodical_name", newPeriodical.getName());
        request.setAttribute("periodical_price", newPeriodical.getPrice());

        return "/periodical_edit.jsp";
    }

    private Periodical GetPeriodicalFromRequest(HttpServletRequest request) {
        String items = request.getParameter("periodicals.periodical_selected");
        StringTokenizer t = new StringTokenizer(items, "|");
        String itemId = t.nextToken();

        return adminService.getPeriodical(new Long(itemId.trim()).longValue());
    }

    private List<Publisher> deletePublisherFromList(List<Publisher> old, Publisher publisher) {
        List<Publisher> publishers = new ArrayList<>();

        for (Publisher newPublisher : old) {
            if (!newPublisher.equals(publisher)) {
                publishers.add(newPublisher);
            }
        }
        return publishers;
    }
}
