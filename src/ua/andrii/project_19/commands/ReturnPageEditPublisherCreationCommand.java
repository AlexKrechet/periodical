package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.StringTokenizer;

public class ReturnPageEditPublisherCreationCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public ReturnPageEditPublisherCreationCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("ReturnPagePublisherCreationCommand()");
        List publishersList = adminService.getPublishers();
        request.setAttribute("publisherslist", publishersList);

        Publisher newPublisher = getPublisherFromRequest(request);

        request.setAttribute("publisher_id", newPublisher.getId());
        request.setAttribute("publisher_name", newPublisher.getName());

        return "/publisher_edit.jsp";
    }

    private Publisher getPublisherFromRequest(HttpServletRequest request) {
        String items = request.getParameter("publishers.publisher_selected");
        StringTokenizer t = new StringTokenizer(items, "|");
        String itemId = t.nextToken();

        return adminService.getPublisher(new Long(itemId.trim()).longValue());
    }
}
