package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.exception.WrongPeriodicalDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdatePeriodicalInfoAdminCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(LogOutCommand.class);
    private final AdminService adminService;

    public UpdatePeriodicalInfoAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        LOGGER.debug("UpdatePeriodicalInfoAdminCommand");

        User user = (User) session.getAttribute("user");
        List periodicalsList = adminService.getPeriodicals();
        List publishersList = adminService.getPublishers();

        String periodicalId = request.getParameter("periodical_id");
        String periodicalName = request.getParameter("periodical_name");

        String periodicalPriceString = request.getParameter("periodical_price");
        Publisher periodicalPublisher = GetPublisherFromRequest(request);
        BigDecimal periodicalPrice;

        try {
            checkPrice(periodicalPriceString);
            periodicalPrice = new BigDecimal(periodicalPriceString);
            Periodical newPeriodical = new Periodical(periodicalName, periodicalPublisher, periodicalPrice);
            newPeriodical.setId(new Long(periodicalId.trim()).longValue());

            publishersList = deletePublisherFromList(publishersList, newPeriodical.getPublisher());
            publishersList.add(0, newPeriodical.getPublisher());

            request.setAttribute("periodical_publisherslist", publishersList);
            request.setAttribute("periodicalslist", periodicalsList);

            try {
                boolean result = adminService.updatePeriodical(newPeriodical);
                if (result) {

                    request.setAttribute("message", "Changing is successful. " + newPeriodical.getName() + " is updated");

                    request.setAttribute("periodical_id", newPeriodical.getId());
                    request.setAttribute("periodical_name", newPeriodical.getName());
                    request.setAttribute("periodical_price", newPeriodical.getPrice());

                    periodicalsList = adminService.getPeriodicals();
                    publishersList = adminService.getPublishers();

                    request.setAttribute("periodical_publisherslist", publishersList);
                    request.setAttribute("periodicalslist", periodicalsList);

                    return "/periodical.jsp";

                } else {
                    request.setAttribute("error", "An internal error occurred while trying to update a periodical");
                    request.setAttribute("periodical_id", periodicalId);
                    request.setAttribute("periodical_name", periodicalName);
                    request.setAttribute("periodical_price", periodicalPriceString);

                    publishersList = deletePublisherFromList(publishersList, periodicalPublisher);
                    publishersList.add(0, periodicalPublisher);
                    request.setAttribute("periodical_publisherslist", publishersList);
                    request.setAttribute("periodicalslist", periodicalsList);

                    return "/periodical_edit.jsp";
                }
            } catch (WrongPeriodicalDataException e) {
                request.setAttribute("error", e.getMessage());

                request.setAttribute("periodical_id", periodicalId);
                request.setAttribute("periodical_name", periodicalName);
                request.setAttribute("periodical_price", periodicalPriceString);

                periodicalsList = adminService.getPeriodicals();
                publishersList = adminService.getPublishers();
                publishersList = deletePublisherFromList(publishersList, periodicalPublisher);
                publishersList.add(0, periodicalPublisher);
                request.setAttribute("periodical_publisherslist", publishersList);
                request.setAttribute("periodicalslist", periodicalsList);

                return "/periodical_edit.jsp";
            }

        } catch (WrongPeriodicalDataException e) {
            request.setAttribute("error", e.getMessage());

            request.setAttribute("periodical_id", periodicalId);
            request.setAttribute("periodical_name", periodicalName);
            request.setAttribute("periodical_price", periodicalPriceString);

            periodicalsList = adminService.getPeriodicals();
            publishersList = adminService.getPublishers();
            publishersList = deletePublisherFromList(publishersList, periodicalPublisher);
            publishersList.add(0, periodicalPublisher);
            request.setAttribute("periodical_publisherslist", publishersList);
            request.setAttribute("periodicalslist", periodicalsList);

            return "/periodical_edit.jsp";
        }
    }

    private Publisher GetPublisherFromRequest(HttpServletRequest request) {
        String items = request.getParameter("periodicals.publisher_selected");
        StringTokenizer t = new StringTokenizer(items, "|");
        String itemId = t.nextToken();

        return adminService.getPublisher(new Long(itemId.trim()).longValue());
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

    private void checkPrice(String price) throws WrongPeriodicalDataException {
        if (price.isEmpty() || price == null) {
            throw new WrongPeriodicalDataException("Price is a required field!");
        }
        Pattern
                pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(price);
        if (!matcher.find()) {
            throw new WrongPeriodicalDataException("Price must contain only digit");
        }
    }
}
