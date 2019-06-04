package ua.andrii.project_19.factory;

import ua.andrii.project_19.commands.*;
import ua.andrii.project_19.enums.DaoType;
import ua.andrii.project_19.enums.ServiceType;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static Map<String, Command> commands;

    static {
        ServiceFactory serviceFactory;
        DaoFactory daoFactory = DaoFactory.getFactory(DaoType.JDBC);
        serviceFactory = ServiceFactory.getFactory(daoFactory, ServiceType.SIMPLE);

        commands = new HashMap<>();
        commands.put("", new IndexJspCommand());

        commands.put("ADD", new AddToCartCommand(serviceFactory.getClientService()));
        commands.put("DELETE", new DeleteFromCartCommand(serviceFactory.getClientService()));
        commands.put("CHECKOUT", new CheckOutCommand());
        commands.put("SEND_ORDER", new SendOrderCommand(serviceFactory.getClientService()));

        commands.put("AUTHENTICATE", new AuthenticateCommand(serviceFactory.getAdminService()));
        commands.put("CLIENT_REGISTRATION", new ClientRegistrationCommand());

        commands.put("USER_INFO", new UserInfoCommand());
        commands.put("UPDATE_USER_PASSWORD", new UserPasswordChangeCommand());
        commands.put("NEW_USER_PASSWORD", new newUserPasswordCommand(serviceFactory.getAdminService()));

        commands.put("ADD_NEW_USER", new AddNewUserCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_USER_BY_ADMIN", new AddNewUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_USER", new MenuUpdateUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("SELECT_USER", new SelectUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_USER_INFO", new UpdateUserInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("PUBLISHER_CREATE_MENU", new PublisherCreateCommand(serviceFactory.getAdminService()));
        commands.put("PUBLISHER_CREATE_LINK", new ReturnPagePublisherCreationCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_PUBLISHER_BY_ADMIN", new AddNewPublisherCommand(serviceFactory.getAdminService()));
        commands.put("EDIT_PUBLISHER_LINK", new ReturnPageEditPublisherCreationCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_PUBLISHER_INFO", new UpdatePublisherInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("PERIODICAL_CREATE_MENU", new PeriodicalCreateCommand(serviceFactory.getAdminService()));
        commands.put("PERIODICAL_CREATE_LINK", new ReturnPagePeriodicalCreationCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_PERIODICAL_BY_ADMIN", new AddNewPeriodicalCommand(serviceFactory.getAdminService()));
        commands.put("EDIT_PERIODICAL_LINK", new ReturnPageEditPeriodicalCreationCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_PERIODICAL_INFO", new UpdatePeriodicalInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("ORDER_CREATE_MENU_ADMIN", new OrderCreateAdminCommand(serviceFactory.getAdminService()));
        commands.put("DELETE_ORDER_ADMIN", new DeleteOrderAdminCommand(serviceFactory.getAdminService()));
        commands.put("PAY_ORDER_ADMIN", new SetPaidOrderAdminCommand(serviceFactory.getAdminService()));

        commands.put("CLIENT_INFO", new ClientInfoCommand());
        commands.put("ORDER_CREATE_MENU_CLIENT", new OrderCreateClientCommand(serviceFactory.getAdminService()));


        commands.put("LOGOUT", new LogOutCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
