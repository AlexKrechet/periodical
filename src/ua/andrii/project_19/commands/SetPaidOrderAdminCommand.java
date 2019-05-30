package ua.andrii.project_19.commands;

import org.apache.log4j.Logger;
import ua.andrii.project_19.entity.Order;
import ua.andrii.project_19.exception.WrongOrderDataException;
import ua.andrii.project_19.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetPaidOrderAdminCommand extends Command {
    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public SetPaidOrderAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("SetPaidOrderAdminCommand()");
        HttpSession session = request.getSession(true);

        List ordersList = (List) session.getAttribute("orderslist");
        //List ordersList=(List) request.getAttribute("orderslist");

        String del = request.getParameter("paidindex");
        int index = (new Integer(del)).intValue();
        Order order = (Order) ordersList.get(index);
        order.setPaid(true);

        try {
            boolean result = adminService.updateOrder(order);
            if (result) {

                request.setAttribute("message", "Updating is successful");
                session.setAttribute("orderslist", ordersList);
                return "/admin_orders.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to update an order");
            }
        } catch (WrongOrderDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        session.setAttribute("orderslist", ordersList);
        //request.setAttribute("orderslist", ordersList);

        return "/admin_orders.jsp";
    }
}
