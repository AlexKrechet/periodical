package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.exception.WrongPublisherDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdatePublisherInfoAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);
    private final AdminService adminService;

    public UpdatePublisherInfoAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        logger.debug("UpdatePublisherInfoAdminCommand");

        User user = (User) session.getAttribute("user");
        List publishersList = adminService.getPublishers();
        request.setAttribute("publisherslist", publishersList);

        String publisherId = request.getParameter("publisher_id");
        Publisher newPublisher = adminService.getPublisher(new Long(publisherId.trim()).longValue());
        String publisherName = request.getParameter("publisher_name");
        newPublisher.setName(publisherName);

        try {
            boolean result = adminService.updatePublisher(newPublisher);
            if (result) {

                request.setAttribute("message", "Changing is successful. " + newPublisher.getName() + " is updated");

                request.setAttribute("publisher_id", newPublisher.getId());
                request.setAttribute("publisher_name", newPublisher.getName());

                publishersList = adminService.getPublishers();
                request.setAttribute("publisherslist", publishersList);
                return "/publisher.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new publisher");
            }
        } catch (WrongPublisherDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("publisher_id", newPublisher.getId());
        request.setAttribute("publisher_name", newPublisher.getName());

        return "/publisher_edit.jsp";
    }
}
