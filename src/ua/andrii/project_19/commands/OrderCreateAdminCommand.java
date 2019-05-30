package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderCreateAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public OrderCreateAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("OrderCreateAdminCommand()");
        HttpSession session = request.getSession(true);

        List periodicalsList = adminService.getPeriodicals();
        request.setAttribute("periodicalslist", periodicalsList);

        List ordersList = adminService.getOrders();

        //request.setAttribute("orderslist", ordersList);
        session.setAttribute("orderslist", ordersList);

        return "/admin_orders.jsp";
    }
}
