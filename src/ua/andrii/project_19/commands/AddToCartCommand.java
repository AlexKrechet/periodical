package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.PeriodicalOrder;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.service.ClientService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AddToCartCommand extends Command {
    private static final Logger logger = Logger.getLogger(ClientService.class);
    private final ClientService clientService;

    public AddToCartCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AddToCartCommand");
        HttpSession session = request.getSession(true);

        List buylist = (List) session.getAttribute("shoppingcart");

        boolean match = false;
        PeriodicalOrder newPeriodicalOrder = GetPeriodical(request);
        if (buylist == null) {
            //add first periodical to the tray
            buylist = new ArrayList(); //first order
            buylist.add(newPeriodicalOrder);
        } else { // not first order
            for (int i = 0; i < buylist.size(); i++) {
                PeriodicalOrder periodicalOrder = (PeriodicalOrder) buylist.get(i);
                if (periodicalOrder.getPeriodical().getId().equals(newPeriodicalOrder.getPeriodical().getId())) {
                    periodicalOrder.setPeriodicalQuantity(periodicalOrder.getPeriodicalQuantity() + newPeriodicalOrder.getPeriodicalQuantity());
                    buylist.set(i, periodicalOrder);
                    match = true;
                }
            }
            if (!match)
                buylist.add(newPeriodicalOrder);
        }
        session.setAttribute("shoppingcart", buylist);

        List periodicalsList = clientService.getPeriodicals();
        request.setAttribute("periodicalslist", periodicalsList);

        return "/order.jsp";
    }

    private PeriodicalOrder GetPeriodical(HttpServletRequest request) {
        String requestPeriodical = request.getParameter("order.periodical_selected");
        String qty = request.getParameter("qty");
        StringTokenizer t = new StringTokenizer(requestPeriodical, "|");
        String periodicalId = t.nextToken();
        String periodicalName = t.nextToken();
        String publisherId = t.nextToken();
        String publisherName = t.nextToken();
        String price = t.nextToken();
        price = price.replace('$', ' ').trim();
        Publisher publisher = new Publisher.Builder().withName(publisherName).build();
        publisher.setId(new Long(publisherId.trim()).longValue());
        Periodical periodical = new Periodical(periodicalName, publisher, new BigDecimal(price));
        periodical.setId(new Long(periodicalId.trim()).longValue());
        PeriodicalOrder periodicalOrder = new PeriodicalOrder.Builder().withPeriodical(periodical).withQuantity(new Integer(qty.trim())).build();
        return periodicalOrder;
    }
}


