package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.exception.WrongPublisherDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddNewPublisherCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AddNewPublisherCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AddNewPublisherCommand()");

        String name = request.getParameter("name");
        try {
            boolean result = adminService.addNewPublisher(name);
            if (result) {
                List publishersList = adminService.getPublishers();
                request.setAttribute("publisherslist", publishersList);
                request.setAttribute("message", "Publisher creation is successful. " + name + " is added");
                return "/publisher.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new publisher");
            }
        } catch (WrongPublisherDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        List publishersList = adminService.getPublishers();
        request.setAttribute("publisherslist", publishersList);
        return "/publisher_create_new.jsp";
    }
}
